package com.test.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Created by:chenxu
 * @Created date:2021/1/5 23:50
 * pay more attention to the interface
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    private static String START_TIME = "start_time";
    //record interface time here is the beginning
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //return super.preHandle(request, response, handler);
        String url = request.getRequestURI().toString();
        Map parameterMap = request.getParameterMap();
        request.setAttribute(START_TIME,System.currentTimeMillis());
        log.info("request start url:{},params:{}",url,parameterMap);
        return  true;
    }

    //right situation using
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String url = request.getRequestURI().toString();
//        Map parameterMap = request.getParameterMap();
//        Long start = (Long) request.getAttribute(START_TIME);
//        Long end = System.currentTimeMillis();
//        log.info("request end url:{},cost:{}",url,end-start);
        removeThreadLocalInfo();
    }

    //always using
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI().toString();
//        Map parameterMap = request.getParameterMap();
        Long start = (Long) request.getAttribute(START_TIME);
        Long end = System.currentTimeMillis();
        log.info("request complete url:{},cost:{}",url,end-start);
//        log.info("request exception url:{},params:{}",url,parameterMap);
        removeThreadLocalInfo();
    }

    public void removeThreadLocalInfo(){
        RequestHolder.remove();
    }
}
