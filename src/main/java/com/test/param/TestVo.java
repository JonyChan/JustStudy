package com.test.param;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @Created by:chenxu
 * @Created date:2021/1/4 21:28
 */
@Data
public class TestVo {

    //自定义msg be clear to the people who is not programmer
    @NotNull(message = ".....")
    @Max(value = 10,message = "should be smaller than 10")
    @Min(value = 0,message = "should be bigger than 0")
    private Long id;

    @NotBlank
    private String msg;

    @NotEmpty
    private List<String> str;
}
