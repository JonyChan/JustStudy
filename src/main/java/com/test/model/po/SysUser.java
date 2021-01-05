package com.test.model.po;

import lombok.Data;

import java.util.Date;

@Data
public class SysUser {

    private Long id;

    private String username;

    private String password;

    private String phone;

    private String mail;

    private Long deptId;

    private Integer status;

    private String remark;

    private String operator;

    private String operatorIp;

    private Date operatorTime;


}