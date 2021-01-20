package com.test.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @Created by:chenxu
 * @Created date:2021/1/20 22:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    private String subject;

    private String message;
    
    private Set<String> receivers;

    

}
