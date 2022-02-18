package com.bshhr.docgenerator.config.security.handle;

import com.bshhr.docgenerator.common.response.ResponseCode;
import com.bshhr.docgenerator.common.response.RestResponse;
import com.bshhr.docgenerator.utils.ServletUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginUserAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        ServletUtils.render(response, RestResponse.build(ResponseCode.NO_AUTHENTICATION));
    }
}