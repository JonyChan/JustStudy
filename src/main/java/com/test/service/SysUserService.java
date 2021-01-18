package com.test.service;

import com.google.common.base.Preconditions;
import com.test.dao.SysUserMapper;
import com.test.exception.ParamException;
import com.test.model.po.SysUser;
import com.test.param.UserParam;
import com.test.util.BeanValidator;
import com.test.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Created by:chenxu
 * @Created date:2021/1/17 16:43
 */
@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    public void save(UserParam param){
        BeanValidator.check(param);

        if (checkEmailExist(param.getMail(), param.getId())){
            throw new ParamException("this email has existed");
        }

        if (checkPhoneExist(param.getTelephone(), param.getId())){
            throw new ParamException("this phone has existed");
        }

        String password = "1234";
//        PasswordUtil.randomPassword();
        String encrypt = MD5Util.encrypt(password);
        SysUser sysUser = SysUser.builder()
                .username(param.getUsername())
                .phone(param.getTelephone())
                .mail(param.getMail())
                .password(encrypt)
                .deptId(param.getDeptId())
                .status(param.getStatus())
                .remark(param.getRemark())
                .build();
        sysUser.setOperator("System");
        sysUser.setOperatorIp("127.0.0.1");
        sysUser.setOperatorTime(new Date());

        sysUserMapper.insertSelective(sysUser);
    }

    public void update(UserParam param){
        BeanValidator.check(param);
        if (checkEmailExist(param.getMail(), param.getId())){
            throw new ParamException("this email has existed");
        }

        if (checkPhoneExist(param.getTelephone(), param.getId())){
            throw new ParamException("this phone has existed");
        }

        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"should not be null");

        SysUser after =  SysUser.builder()
                .id(param.getId())
                .username(param.getUsername())
                .phone(param.getTelephone())
                .mail(param.getMail())
                .password(before.getPassword())
                .deptId(param.getDeptId())
                .status(param.getStatus())
                .remark(param.getRemark())
                .build();
        after.setOperator("System");
        after.setOperatorIp("127.0.0.1");
        after.setOperatorTime(new Date());

        sysUserMapper.updateByPrimaryKeySelective(after);
    }

    public boolean checkEmailExist(String email, Long userId){
        return sysUserMapper.countByMail(email,userId)>0;
    }

    public boolean checkPhoneExist(String phone, Long userId){
        return sysUserMapper.countByPhone(phone, userId)>0;
    }

    public SysUser findByKeyword(String keyword){
        return sysUserMapper.findByKeyword(keyword);
    }
}
