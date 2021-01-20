package com.test.beans;


import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Created by:chenxu
 * @Created date:2021/1/19 21:55
 */

@Data
@Builder
public class PageResult<T> {

    private List<T> data = Lists.newArrayList();

    private int total = 0;
}
