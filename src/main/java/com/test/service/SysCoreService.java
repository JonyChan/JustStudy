package com.test.service;

import org.springframework.stereotype.Service;

/**
 * @Created by:chenxu
 * @Created date:2021/2/5 17:01
 */
@Service
public class SysCoreService {


    public boolean isSuperadmin(){
        return true;
    }

    //自定义的
    public boolean hasAclUrl(String path){
        if (isSuperadmin()){
            return true;
        }
        //权限拦截自定义的
        return true;
    }
}
