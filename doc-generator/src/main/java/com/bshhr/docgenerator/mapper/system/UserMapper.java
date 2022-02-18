package com.bshhr.docgenerator.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bshhr.docgenerator.domain.system.entity.SysUser;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<SysUser> {
    @Select("select * from sys_user where user_name = #{userName} limit 1")
    SysUser findByUsername(String userName);
}
