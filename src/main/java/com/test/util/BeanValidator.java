package com.test.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * @Created by:chenxu
 * @Created date:12/31/20 12:46 PM
 */
public class BeanValidator {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T>Map<String,String> validate(T t,Class... groups){
        Validator validator = validatorFactory.getValidator();
        Set validateRes = validator.validate(t,groups);
        if (validateRes.isEmpty()){
            return Collections.emptyMap();
        }else{
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator iterator = validateRes.iterator();
            while (iterator.hasNext()){
                //constrainViolation是一个返回的错误的对象封装的类型，包含错误的字段以及错误的信息
                ConstraintViolation violation = (ConstraintViolation)iterator.next();
                errors.put(violation.getPropertyPath().toString(),violation.getMessage());
            }
            return errors;
        }
    }
    public static Map<String,String> validateList(Collection<?> collection){
        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();
        Map errors;
        do{
            if (!iterator.hasNext()){
                return Collections.emptyMap();
            }
            errors = validate(iterator.next(), new Class[0]);
        }while (errors.isEmpty());
        return errors;
    }
}
