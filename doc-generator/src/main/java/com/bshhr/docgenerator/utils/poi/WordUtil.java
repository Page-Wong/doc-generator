package com.bshhr.docgenerator.utils.poi;

import com.bshhr.docgenerator.utils.uuid.IdUtils;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class WordUtil {
    public static StringBuilder readDocFile(MultipartFile file){
        String textFileName=file.getOriginalFilename();
        StringBuilder doc = new StringBuilder();
        try {
            if(textFileName.endsWith(".doc")){ //判断文件格式
                InputStream fis = file.getInputStream();
                WordExtractor wordExtractor = new WordExtractor(fis);//使用HWPF组件中WordExtractor类从Word文档中提取文本或段落
                for(String words : wordExtractor.getParagraphText()){//获取段落内容
                    doc.append(words).append(System.getProperty("line.separator"));
                }
                fis.close();
            }
            if(textFileName.endsWith(".docx")){
                String tmpFileName = String.format("%s.docx", IdUtils.fastSimpleUUID());
                File uFile = new File(tmpFileName);//创建一个临时文件
                if(!uFile.exists()){
                    uFile.createNewFile();
                }
                FileCopyUtils.copy(file.getBytes(), uFile);//复制文件内容
                OPCPackage opcPackage = POIXMLDocument.openPackage(tmpFileName);//包含所有POI OOXML文档类的通用功能，打开一个文件包。
                XWPFDocument document = new XWPFDocument(opcPackage);//使用XWPF组件XWPFDocument类获取文档内容
                List<XWPFParagraph> paras = document.getParagraphs();
                for(XWPFParagraph paragraph : paras){
                    String words = paragraph.getText();
                    doc.append(words).append(System.getProperty("line.separator"));
                }
                List<XWPFTable> tables = document.getTables();
                for (XWPFTable table: tables) {
                    CTTbl ctTbl = table.getCTTbl();
                    // 获取表格所有行
                    List<CTRow> trList = ctTbl.getTrList();
                    for (CTRow row: trList) {
                        // 当前行属性 <w:trPr>
                        CTTrPr trPr = row.getTrPr();
                        // 获取当前行内所有单元格 <w:tc>
                        List<CTTc> tcList = row.getTcList();
                        for (CTTc tc:tcList) {
                            // 当前单元格属性  <w:tcPr>
                            CTTcPr tcPr = tc.getTcPr();
                            // 获取当前单元格内所有段落 <w:p>
                            List<CTP> pList = tc.getPList();
                            for (CTP p:pList){
                                // 当前段落属性 <w:pPr>
                                CTPPr pPr = p.getPPr();
                                // 获取当前段落所有文本区域 <w:r>
                                List<CTR> rList = p.getRList();
                                for (CTR r:rList){
                                    // 获取所有图片
                                    List<CTDrawing> drawingList =  r.getDrawingList();
                                    for (CTDrawing drawing:drawingList){
                                        // 获取文字
                                        List<CTInline> inlineList = drawing.getInlineList();
                                        for (CTInline inline:inlineList){
                                            String descr = inline.getDocPr().getDescr();
                                            doc.append(descr);
                                        }
                                    }

                                    // 获取所有文本
                                    List<CTText> tList = r.getTList();
                                    for (CTText text:tList){
                                        // 获取文字
                                        String stringValue = text.getStringValue();
                                        doc.append(stringValue);
                                    }
                                }
                            }
                        }
                    }
                }
                uFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
}
