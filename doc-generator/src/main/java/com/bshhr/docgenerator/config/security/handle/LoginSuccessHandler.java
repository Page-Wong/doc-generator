package com.bshhr.docgenerator.config.security.handle;

import com.bshhr.docgenerator.common.ip.IpUtils;
import com.bshhr.docgenerator.common.response.RestResponse;
import com.bshhr.docgenerator.domain.system.entity.SysUser;
import com.bshhr.docgenerator.domain.system.model.LoginUser;
import com.bshhr.docgenerator.mapper.system.UserMapper;
import com.bshhr.docgenerator.service.security.TokenService;
import com.bshhr.docgenerator.utils.DateUtils;
import com.bshhr.docgenerator.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        RestResponse restResponse = RestResponse.success();
        // 生成令牌
        String token = tokenService.createToken(loginUser);
        restResponse.setData(token);
        recordLoginInfo(loginUser.getId());
        // TODO 登录成功 记录日志
        ServletUtils.render(response, restResponse);
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(String userId)
    {
        SysUser sysUser = userMapper.selectById(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        sysUser.setLoginDate(DateUtils.getNowDate());
        userMapper.updateById(sysUser);
    }
}
