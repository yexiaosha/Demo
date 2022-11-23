package com.yhdemo.demo.handler;

import com.yhdemo.demo.vo.ErrorCode;
import com.yhdemo.demo.vo.Result;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 参数校验统一异常处理
 * @author wyh
 * @date 2022/11/22 16:24
 */

@RestControllerAdvice
@Order(0)
@Slf4j
public class ValidateExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Result validateErrorHandler(BindException e) {
        ObjectError error = e.getAllErrors().get(0);
        log.info("数据验证异常：{}", error.getDefaultMessage());
        return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result validateErrorHandler(MethodArgumentNotValidException e) {
        ObjectError error = e.getAllErrors().get(0);
        log.info("数据验证错误：{}", error.getDefaultMessage());
        return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result validateErrorHandler(ConstraintViolationException e) {
        log.info("数据验证异常：", e);
        return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
    }

}
