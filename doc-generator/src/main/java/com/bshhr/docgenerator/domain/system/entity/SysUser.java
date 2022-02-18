package com.bshhr.docgenerator.domain.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bshhr.docgenerator.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_user")
public class SysUser extends BaseEntity {

    /**
     * 登录名称
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobileNumber;

    /**
     * 用户性别（0=男,1=女,2=未知）
     */
    private Integer sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private Date loginDate;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private Integer delFlag;
}
