package com.test.dao;

import com.test.model.po.SysPermissionModule;

public interface SysPermissionModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysPermissionModule record);

    int insertSelective(SysPermissionModule record);

    SysPermissionModule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermissionModule record);

    int updateByPrimaryKey(SysPermissionModule record);

    int countByNameAndParentId();
}