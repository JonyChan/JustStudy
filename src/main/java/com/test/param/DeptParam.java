package com.test.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Created by:chenxu
 * @Created date:2021/1/6 20:41
 */
@Data
public class DeptParam {

    private Long id;

    @NotBlank
    @Length(max = 15,min = 2,message = "name length should be limitation")
    private String name;

    private Long parentId;

    @NotNull(message = "can not be null")
    private Integer seq;

    private String remark;
}
