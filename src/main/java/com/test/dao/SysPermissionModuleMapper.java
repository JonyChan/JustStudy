package com.test.dao;

import com.test.model.po.SysPermissionModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysPermissionModule record);

    int insertSelective(SysPermissionModule record);

    SysPermissionModule selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(SysPermissionModule record);

    int updateByPrimaryKey(SysPermissionModule record);

    int countByNameAndParentId(@Param("parentId") Long parentId, @Param("name") String name, @Param("id") Long deptId);

    List<SysPermissionModule> getChildAclModuleListByLevel(@Param("level") String level);

    void batchUpdateLevel(@Param("sysAclModuleList") List<SysPermissionModule> sysAclModuleList);

    List<SysPermissionModule> getAllAclModule();

    int countByParentId(@Param("aclModuleId") Long aclModuleId);
}