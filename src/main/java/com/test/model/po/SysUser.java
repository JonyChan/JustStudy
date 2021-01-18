package com.test.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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