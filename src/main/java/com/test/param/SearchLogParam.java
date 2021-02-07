package com.test.param;

import lombok.Data;

/**
 * @Created by:chenxu
 * @Created date:2021/2/7 16:43
 */
@Data
public class SearchLogParam {

    // LogType
    private Integer type;

    private String beforeSeg;

    private String afterSeg;

    private String operator;

    //yyyy-MM-dd HH:mm:ss
    private String fromTime;

    private String toTime;

}
