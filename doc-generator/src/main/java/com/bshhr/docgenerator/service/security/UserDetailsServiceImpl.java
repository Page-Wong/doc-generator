package com.bshhr.docgenerator.service.security;

import com.bshhr.docgenerator.domain.system.entity.SysUser;
import com.bshhr.docgenerator.domain.system.model.LoginUser;
import com.bshhr.docgenerator.mapper.system.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userMapper.findByUsername(username);
        if (sysUser == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return createLoginUser(sysUser);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user.getId(), user);
    }
}
