package com.test.beans;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * @Created by:chenxu
 * @Created date:2021/1/19 21:55
 */
public class PageQuery {

    @Getter
    @Setter
    @Min(value = 1,message = "the value is invalid")
    private int pageNo = 1;

    @Getter
    @Setter
    @Min(value = 1,message = "the value is invalid")
    private int pageSize = 1;

    //偏移量
    @Setter
    private int offset;

    public int getOffset(){
        return (pageNo-1)*pageSize;
    }

}
