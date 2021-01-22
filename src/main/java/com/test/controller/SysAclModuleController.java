package com.test.controller;

import com.test.common.JsonData;
import com.test.param.AclModuleParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Created by:chenxu
 * @Created date:2021/1/21 12:29
 */

@Controller
@RequestMapping("/sys/acl")
public class SysAclModuleController {

    @RequestMapping("/acl.page")
    public ModelAndView page(){
        return new ModelAndView("acl");
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData save(AclModuleParam param){

        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData update(AclModuleParam param){

        return JsonData.success();
    }

}
