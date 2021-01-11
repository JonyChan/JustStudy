package com.test.controller;

import com.test.common.ApplicationContextHelper;
import com.test.common.JsonData;
import com.test.exception.ParamException;
import com.test.dao.SysUserMapper;
import com.test.model.po.SysUser;
import com.test.param.TestVo;
import com.test.util.BeanValidator;
import com.test.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Created by:chenxu
 * @Created date:12/29/20 10:50 PM
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @ResponseBody
    @RequestMapping("/hello.json")
    public JsonData home(){
        log.info("home and family");
        return JsonData.success("hello");
    }

    @ResponseBody
    @RequestMapping("/validate1.json")
    public JsonData validate(TestVo vo){
        log.info("validate");
        try{
            Map<String,String> res = BeanValidator.validateObject(vo);
            if (MapUtils.isNotEmpty(res)){
                for (Map.Entry<String,String> entry:res.entrySet()){
                    log.info("{}->{}",entry.getKey(),entry.getValue());
                }
            }
        }catch (Exception e){

        }
        return JsonData.success("hello");
    }
//console the exception and throw to the frontEnd
    //1 ParamException extends RuntimeException 2 VO limititaion
    //as same as the before one
    @ResponseBody
    @RequestMapping("/validate2.json")
    public JsonData validate2(TestVo vo) throws ParamException {
        log.info("validate");
        SysUserMapper userMapper = ApplicationContextHelper.popBean(SysUserMapper.class);
        SysUser user = userMapper.selectByPrimaryKey(1L);
        log.info(JsonMapper.obj2String(user));
        BeanValidator.check(vo);
        return JsonData.success("hello");
    }
}
