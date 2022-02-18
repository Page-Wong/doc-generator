package com.bshhr.docgenerator.utils.poi;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.bshhr.docgenerator.domain.system.model.ExcelData;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Excel相关处理
 * 
 * @author hbz
 */
public class ExcelUtil
{
    public static List<Map<String, Object>> readExcel(File file){
        List<Map<String, Object>> maps = new LinkedList<>();

        EasyExcel.read(file, ExcelData.class, new PageReadListener<ExcelData>(dataList -> {
            for (ExcelData row : dataList) {
                System.out.println(row);
            }
        })).sheet().doRead();

        return maps;
    }
}
