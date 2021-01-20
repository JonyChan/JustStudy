package com.test.dao;

import com.test.beans.PageQuery;
import com.test.model.po.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser findByKeyword(@Param("keyword") String keyword);

    int countByMail(@Param("mail") String mail, @Param("id") Long id);

    int countByPhone(@Param("phone") String phone, @Param("id") Long id);

    int countByDeptId(@Param("deptId") Long deptId);

    List<SysUser> getPageByDeptId(@Param("deptId") Long deptId,@Param("query") PageQuery query);
}