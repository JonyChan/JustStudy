package com.test.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @Created by:chenxu
 * @Created date:2021/1/6 21:12
 */
public class LevelUtil {

    public final static String SEPARATOR = ".";

    public final static String ROOT = "0";

    public static String calculatorLevel(String parentLevel, Long parentId){
        if (StringUtils.isBlank(parentLevel)){
            return ROOT;
        }else {
            return StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }

}
