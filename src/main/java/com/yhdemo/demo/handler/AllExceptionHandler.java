package com.yhdemo.demo.handler;

import com.yhdemo.demo.pojo.vo.Result;
import com.yhdemo.demo.utils.ResultUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一接口异常处理
 * 对@Controller进行异常处理
 * @author wyh
 * @data 2022/11/18 17:35
 */

@RestControllerAdvice
public class AllExceptionHandler {

    /**
     * 通用异常处理
     * @param ex 异常类型
     * @return 异常返回类型
     */
    @ExceptionHandler(Exception.class)
    public Result<Boolean> doException(Exception ex) {
        ex.printStackTrace();
        return ResultUtils.fail("-9999", "系统异常");
    }
}
