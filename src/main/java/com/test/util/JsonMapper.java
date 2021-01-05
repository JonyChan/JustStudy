package com.test.util;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

/**
 * @Created by:chenxu
 * @Created date:2021/1/5 21:04
 * Json to class, class to json
 */
@Slf4j
public class JsonMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    // init global variable
    static {
        //exclude the null column property
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    public static <T> String obj2String(T t){
        if (t==null){
            return null;
        }
        try{
            return t instanceof String ? (String)t : objectMapper.writeValueAsString(t);
        }catch (Exception e){
            log.warn("parse object exception,error:{}",e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, TypeReference<T> typeReference){
        if (str==null||typeReference==null){
            return null;
        }
        try {
            return (T)(typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str,typeReference));
        }catch (Exception e){
            log.warn("parse String exception,String:{},TypeReference:{}",e,typeReference);
            return null;
        }
    }

}
