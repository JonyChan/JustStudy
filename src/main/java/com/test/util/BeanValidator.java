package com.test.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.test.exception.ParamException;
import org.apache.commons.collections.MapUtils;

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

    //define  a  factory
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    //T replace Type
    public static <T>Map<String,String> validate(T t,Class... groups){
        Validator validator = validatorFactory.getValidator();
        Set validateRes = validator.validate(t,groups);
        if (validateRes.isEmpty()){
            return Collections.emptyMap();
        }else{
            //use map generate LinkedHashMap
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator iterator = validateRes.iterator();
            while (iterator.hasNext()){
                //constrainViolation是一个返回的错误的对象封装的类型，包含错误的字段以及错误的信息
                ConstraintViolation violation = (ConstraintViolation)iterator.next();
                //error type and error type value
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

    //Console two methods
    public static Map<String,String> validateObject(Object first,Object... objects){
        if (objects!=null&&objects.length>0){
            return validateList(Lists.asList(first,objects));
        }else {
            return validate(first,new Class[0]);
        }
    }

    public static  void check(Object param) throws ParamException {
        Map<String,String> map = BeanValidator.validateObject(param);
        if (MapUtils.isNotEmpty(map)){
            throw new ParamException(map.toString());
        }
    }

}
