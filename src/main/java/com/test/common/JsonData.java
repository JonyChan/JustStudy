package com.test.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Created by:chenxu
 * @Created date:12/30/20 11:06 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonData {

    private boolean ret;
    private String msg;
    private Object data;

    public JsonData(boolean ret){
        this.ret=ret;

    }
    public static JsonData success(Object data,String msg){
        JsonData jd = new JsonData(true);
        jd.data=data;
        jd.msg=msg;
        return jd;
    }

    public static JsonData success(Object data){
        JsonData jd = new JsonData();
        jd.data=data;
        return jd;
    }

    public static JsonData success(String msg){
        JsonData jd = new JsonData(true);
        jd.msg=msg;
        return jd;
    }

    public static JsonData success(){
        return new JsonData(true);
    }

    public static JsonData fail(String msg){
        JsonData jd = new JsonData(false);
        jd.msg=msg;
        return jd;
    }

    public Map<String,Object> toMap(){
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("ret",ret);
        result.put("msg",msg);
        result.put("data",data);
        return result;
    }

}
