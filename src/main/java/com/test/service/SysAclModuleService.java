package com.test.service;

import com.test.dao.SysPermissionModuleMapper;
import com.test.model.po.SysPermissionModule;
import com.test.param.AclModuleParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Created by:chenxu
 * @Created date:2021/1/21 12:40
 */
@Service
public class SysAclModuleService {

    @Resource
    private SysPermissionModuleMapper sysPermissionModuleMapper;

    public void save(AclModuleParam param){

    }



}
