package com.bshhr.docgenerator.controller;

import com.bshhr.docgenerator.common.response.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public RestResponse Test(){
        return RestResponse.success();
    }
}
