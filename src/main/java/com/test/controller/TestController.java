package com.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Created by:chenxu
 * @Created date:12/29/20 10:50 PM
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @ResponseBody
    @RequestMapping("/hello")
    public String home(){
        log.info("home and family");
        return "home";
    }
}