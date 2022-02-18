package com.bshhr.docgenerator.service.poi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

public interface PoiTlService {
    public void generatorAsFile(File templateDoc, Map<String, BufferedImage> pictures, String params, String targetPath);
}
