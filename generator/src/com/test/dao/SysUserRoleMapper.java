package com.test.dao;

import com.test.model.SysUserRole;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}