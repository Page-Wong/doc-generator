package com.bshhr.docgenerator.service.poi;

import com.bshhr.docgenerator.domain.system.model.PoiTlModel;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.PictureType;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.plugin.table.LoopColumnTableRenderPolicy;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.cglib.beans.BeanMap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoiTlServiceImpl implements PoiTlService{
    @SneakyThrows
    @Override
    public void generatorAsFile(File templateDoc, Map<String, BufferedImage> pictures, String params, String targetPath) {
        ObjectMapper mapper = new ObjectMapper();
        List<PoiTlModel> poiDataList = mapper.readValue(params, new TypeReference<List<PoiTlModel>>() {});
        Map<String, Object> map = new HashMap<>();
        ConfigureBuilder configBuilder = Configure.builder().buildGramer("$", "$");
        for (PoiTlModel item : poiDataList) {
            if (item.data == null || item.data.isEmpty()){
                continue;
            }
            switch (item.type){
                case TEXT:
                    TextRenderData textRenderData = new TextRenderData();
                    BeanMap.create(textRenderData).putAll(item.data.get(0));
                    map.put(item.name, textRenderData);
                    break;
                case PICTURE:
                    BufferedImage img = pictures.get(item.name);
                    if (img != null) {
                        HashMap<String, Object> pictureData = item.data.get(0);
                        int width = (int)pictureData.get("width");
                        int height = (int)pictureData.get("height");
                        PictureType pictureType = PictureType.valueOf(pictureData.get("pictureType").toString());
                        PictureRenderData pictureRenderData = new PictureRenderData(width, height, pictureType, img);
                        map.put(item.name, pictureRenderData);
                    }
                    break;
                case TABLE:
                    TableRenderData tableRenderData = new TableRenderData();
                    BeanMap.create(tableRenderData).putAll(item.data.get(0));
                    map.put(item.name, tableRenderData);
                    break;
                case HACK_LOOP_TABLE:
                    configBuilder.bind(item.name, new LoopRowTableRenderPolicy());
                    map.put(item.name, item.data);
                    break;
                case LOOP_COLUMN_TABLE:
                    configBuilder.bind(item.name, new LoopColumnTableRenderPolicy());
                    map.put(item.name, item.data);
                    break;
                default:
                    map.put(item.name, item.data);
                    break;
            }
        }
        XWPFTemplate template = XWPFTemplate.compile(templateDoc, configBuilder.build()).render(map);
        template.writeToFile(targetPath);
    }
}
