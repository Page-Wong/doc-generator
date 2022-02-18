package com.bshhr.docgenerator.mapper.system;

import com.bshhr.docgenerator.domain.system.entity.SysUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
public class SysUserMapperTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        SysUser sysUser = new SysUser();
        sysUser.setUserName("aaa");
        sysUser.setNickName("bbb");
        sysUser.setEmail("aaa@xxx.com");
        sysUser.setMobileNumber("137123456");
        sysUser.setPassword(passwordEncoder.encode("123456"));

        int insert = userMapper.insert(sysUser);
        Assertions.assertEquals(insert, 1);

    }

    @Test
    public void testSelect(){
        List<SysUser> sysUsers = userMapper.selectList(null);
        sysUsers.forEach(System.out::println);
        Assertions.assertEquals(1, sysUsers.size());
    }

    @Test
    void findByUsername() {
        SysUser aaa1 = userMapper.findByUsername("aaa");
        Assertions.assertNotEquals(aaa1, null);
        System.out.println(aaa1);
    }
}