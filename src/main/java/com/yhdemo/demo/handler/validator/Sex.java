package com.yhdemo.demo.handler.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 性别校验
 * @author wyh
 * @date 2022/11/22 17:52
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SexConstraintValidator.class)
public @interface Sex {

    String message() default "性别只能为MALE或FEMALE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
