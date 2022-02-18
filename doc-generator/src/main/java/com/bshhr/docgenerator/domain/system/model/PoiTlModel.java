package com.bshhr.docgenerator.domain.system.model;

import com.bshhr.docgenerator.common.emun.PoiType;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class PoiTlModel {
    public PoiTlModel(){

    }

    public String name;
    public PoiType type;
    public List<HashMap<String, Object>> data;
}

