package com.yhdemo.demo.utils;

import com.yhdemo.demo.pojo.vo.Result;

/**
 * 返回结果组件
 * @author wyh
 * @date 2022/12/01 10:37
 */
public class ResultUtils {

    public static <T> Result<T> success(T data) {
        return new Result<>(true, "00000", "success", data);
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> fail(String code, String msg) {
        return new Result<>(false, code, msg, null);
    }

}
