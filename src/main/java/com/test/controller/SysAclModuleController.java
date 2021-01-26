package com.test.controller;

import com.test.common.JsonData;
import com.test.model.po.SysPermissionModule;
import com.test.param.AclModuleParam;
import com.test.service.SysAclModuleService;
import com.test.service.SysTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Created by:chenxu
 * @Created date:2021/1/21 12:29
 */

@Controller
@RequestMapping("/sys/aclModule")
public class SysAclModuleController {

    @Autowired
    private SysAclModuleService sysAclModuleService;
    @Autowired
    private SysTreeService sysTreeService;

    @RequestMapping("/acl.page")
    public ModelAndView page(){
        return new ModelAndView("acl");
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree(){
        return JsonData.success(sysTreeService.aclModuleTree());
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData save(AclModuleParam param){
        sysAclModuleService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData update(AclModuleParam param){
        sysAclModuleService.update(param);
        return JsonData.success();
    }

}
