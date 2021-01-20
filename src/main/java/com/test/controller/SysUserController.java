package com.test.controller;

import com.test.beans.PageQuery;
import com.test.beans.PageResult;
import com.test.common.JsonData;
import com.test.model.po.SysUser;
import com.test.param.UserParam;
import com.test.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Created by:chenxu
 * @Created date:2021/1/17 16:35
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ResponseBody
    @RequestMapping("/save.json")
    public JsonData save(UserParam userParam){
        sysUserService.save(userParam);
        return JsonData.success();
    }

    @ResponseBody
    @RequestMapping("/update.json")
    public JsonData update(UserParam userParam){
        sysUserService.update(userParam);
        return JsonData.success();
    }

    @ResponseBody
    @RequestMapping("/page.json")
    public JsonData getPages(Long deptId, PageQuery query){
        PageResult<SysUser> users = sysUserService.getPageByDeptId(deptId, query);
        return JsonData.success(users);
    }
}
