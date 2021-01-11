package com.test.service;

import com.google.common.base.Preconditions;
import com.test.dao.SysDeptMapper;
import com.test.exception.ParamException;
import com.test.model.po.SysDept;
import com.test.param.DeptParam;
import com.test.util.BeanValidator;
import com.test.util.LevelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Created by:chenxu
 * @Created date:2021/1/6 21:08
 */
@Service
public class SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam param){
        BeanValidator.validateObject(param);
        if (checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("there is the same dept in the level");
        }
        SysDept dept = SysDept.builder()
                .name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .build();
        dept.setLevel(LevelUtil.calculatorLevel(getLevel(param.getId()), param.getParentId()));
        dept.setOperator("System");
        dept.setOperatorIp("127.0.0.1");
        dept.setOperatorTime(new Date());
        //only insert which is not null
        sysDeptMapper.insertSelective(dept);
    }

    public void update(DeptParam param){
        BeanValidator.validateObject(param);
        if (checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("there is the same dept in the level");
        }

        SysDept before = sysDeptMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"not exist");

        SysDept dept = SysDept.builder()
                .name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .build();
        dept.setLevel(LevelUtil.calculatorLevel(getLevel(param.getId()), param.getParentId()));
        dept.setOperator("System");
        dept.setOperatorIp("127.0.0.1");
        dept.setOperatorTime(new Date());
        //only insert which is not null
        sysDeptMapper.insertSelective(dept);
    }

    private boolean checkExist(Long parentId,String deptName,Long deptId){
        //todo
        return  true;
    }

    private String getLevel(Long deptId){
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (dept == null){
            return null;
        }
        return dept.getLevel();
    }

}
