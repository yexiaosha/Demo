package com.yhdemo.demo.handler.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 邮箱校验
 * @author wyh
 * @date 2022/11/22 17:43
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailConstraintValidator.class)

public @interface Email {

    String message() default "邮箱有误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
