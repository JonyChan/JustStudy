package com.test.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Created by:chenxu
 * @Created date:2021/1/5 21:29
 * get Spring context
 */
//in the can id = applicationContextHelper
@Component("applicationContextHelper")
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    //get bean from springContext
    public static <T> T popBean(Class<T> clazz){
        if (applicationContext==null){
            return null;
        }
        return applicationContext.getBean(clazz);
    }

    public static <T> T popBean(String name, Class<T> clazz){
        if (applicationContext==null){
            return null;
        }
        return applicationContext.getBean(name,clazz);
    }
}
