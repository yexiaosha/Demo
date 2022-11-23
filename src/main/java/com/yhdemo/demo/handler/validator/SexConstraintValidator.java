package com.yhdemo.demo.handler.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 性别校验器
 * @author wyh
 * @date 2022/11/22 17:53
 */
public class SexConstraintValidator implements ConstraintValidator<Sex, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return ("MALE".equals(value) || "FEMALE".equals(value));
    }
}
