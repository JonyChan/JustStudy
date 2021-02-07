package com.test.controller;

import com.test.beans.PageQuery;
import com.test.beans.PageResult;
import com.test.common.JsonData;
import com.test.param.SearchLogParam;
import com.test.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Created by:chenxu
 * @Created date:2021/2/7 15:41
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/log.page")
    public ModelAndView page(){
        return new ModelAndView("log");
    }

    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData getPage(SearchLogParam param, PageQuery page){
        return JsonData.success(sysLogService.getList(param, page));
    }
}
