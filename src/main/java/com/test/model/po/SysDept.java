package com.test.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SysDept {
    private Long id;

    private String name;

    private Long parentId;

    private String level;

    private Integer seq;

    private String remark;

    private String operator;

    private Date operatorTime;

    private String operatorIp;

}