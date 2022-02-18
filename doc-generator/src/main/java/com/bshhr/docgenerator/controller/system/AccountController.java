package com.bshhr.docgenerator.controller.system;

import com.bshhr.docgenerator.common.response.RestResponse;
import com.bshhr.docgenerator.domain.system.entity.SysUser;
import com.bshhr.docgenerator.domain.system.model.LoginUser;
import com.bshhr.docgenerator.utils.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

@RestController
public class AccountController {

    @Resource
    private AuthenticationManager authenticationManager;

//    @GetMapping("/login")
//    public RestResponse login(){
//        return RestResponse.fail(ResponseCode.USER_NEED_LOGIN);
//    }

//    @PostMapping("/login")
//    public RestResponse login(String username, String password, String code, String uuid){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//    }
    @GetMapping("/account")
    public RestResponse get(){
        SysUser user = SecurityUtils.getLoginUser().getUser();

        RestResponse result = RestResponse.success(user);
        return result;
    }
}
