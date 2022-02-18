package com.bshhr.docgenerator.controller;

import com.bshhr.docgenerator.common.response.RestResponse;
import com.bshhr.docgenerator.utils.poi.WordUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
public class GeneratorController {
    @PostMapping("/upload/generator")
    public RestResponse batchGenerator(HttpServletRequest request, HttpServletResponse response){
        List<MultipartFile> docFiles = ((MultipartRequest)request).getFiles("docFile");
        MultipartFile excelFile = ((MultipartRequest)request).getFile("excelFile");
        return RestResponse.success();
    }

    @PostMapping("/upload/doc/param")
    public RestResponse readParams(@RequestParam("file") MultipartFile file){
        StringBuilder word = WordUtil.readDocFile(file);
        Pattern pattern = Pattern.compile("\\{\\{[^}}]+}}");
        Matcher matcher = pattern.matcher(word);

        List<String> docParams = new LinkedList<>();
        while (matcher.find()){
            docParams.add(matcher.group());
        }
        return RestResponse.success(docParams.stream().distinct().collect(Collectors.toList()));
    }
}
