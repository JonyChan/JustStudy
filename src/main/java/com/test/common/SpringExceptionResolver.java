package com.test.common;

import com.test.exception.ParamException;
import com.test.exception.SpringCloudException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Created by:chenxu
 * @Created date:12/30/20 11:23 PM
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        String url = httpServletRequest.getRequestURL().toString();
        String defaultMsg = "";
        ModelAndView mv;

        if (url.endsWith(".json")){
            if (e instanceof SpringCloudException || e instanceof ParamException){
                JsonData res = JsonData.fail(e.getMessage());
                mv= new ModelAndView("jsonView",res.toMap());
            }else {
                log.error("unknown exception, url:"+url,e);
                JsonData res = JsonData.fail(defaultMsg);
                mv= new ModelAndView("jsonView",res.toMap());
            }
        }else if (url.endsWith(".page")){
            log.error("unknown pageException, url:"+url,e);
            JsonData res = JsonData.fail(defaultMsg);
            mv= new ModelAndView("exception",res.toMap());
        }else {
            log.error("unknown pageException, url:"+url,e);
            JsonData res = JsonData.fail(defaultMsg);
            mv= new ModelAndView("jsonView",res.toMap());
        }
        return mv;
    }
}
