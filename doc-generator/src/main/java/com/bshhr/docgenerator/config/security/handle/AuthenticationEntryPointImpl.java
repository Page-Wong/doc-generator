package com.bshhr.docgenerator.config.security.handle;

import com.alibaba.fastjson.JSON;
import com.bshhr.docgenerator.common.response.ResponseCode;
import com.bshhr.docgenerator.common.response.RestResponse;
import com.bshhr.docgenerator.utils.ServletUtils;
import com.bshhr.docgenerator.utils.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 * 
 * @author hbz
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable
{
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException
    {
//        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(RestResponse.build(ResponseCode.UNAUTHORIZED)));
    }
}
