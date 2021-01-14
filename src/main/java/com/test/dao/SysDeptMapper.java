package com.test.dao;

import com.test.model.po.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    List<SysDept> getAllDept();

    List<SysDept> getChildDeptListByLevel(@Param("level") String level);

    void batchUpdateLevel(@Param("sysDeptList") List<SysDept> sysDeptList);

    int countByNameAndParentId(@Param("parentId") Long parentId, @Param("name") String name, @Param("id") Long id);
}