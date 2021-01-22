package com.test.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Created by:chenxu
 * @Created date:2021/1/21 12:31
 */
@Data
public class AclModuleParam {

    private Long id;

    @NotBlank(message = "not null")
    @Length(min = 2,max = 64,message = "length between 2 and 64")
    private String name;

    private Long parentId = 0L;

    @NotNull(message = "not null")
    private Integer seq;

    @NotNull(message = "not null")
    @Min(value = 0, message = "invalid")
    @Max(value = 1, message = "invalid")

    private Integer status;

    @Length(max = 64,message = "length between 2 and 64")
    private String remark;
}
