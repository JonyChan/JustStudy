package com.test.justtest;

import org.apache.commons.lang3.StringUtils;

/**
 * @Created by:chenxu
 * @Created date:2021/1/13 08:43
 */
public class Test {
    public static void main(String [] args){
        String a = "1";
        String b = "+";
        String c = "2";
        System.out.println(StringUtils.join(a,b,c));
    }
}
