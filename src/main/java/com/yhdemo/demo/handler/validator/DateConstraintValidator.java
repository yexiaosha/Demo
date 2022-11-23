package com.yhdemo.demo.handler.validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 日期校验器
 * @author wyh
 * @date 2022/11/22 17:59
 */
public class DateConstraintValidator implements ConstraintValidator<Date, String> {

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        String format = "yyyy-MM-dd";
        DateFormat formatter = new SimpleDateFormat(format);
        try {
            java.util.Date sDate = formatter.parse(date);
            return date.equals(formatter.format(sDate));
        } catch (Exception e) {
            return false;
        }
    }
}
