package com.yhdemo.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用视图返回类型
 * @author wyh
 * @data 2022/11/18 11:29
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    /**
     * 返回事务状态
     */
    private boolean success;
    /**
     * 状态码
     */
    private String code;
    /**
     * 状态信息
     */
    private String msg;
    /**
     * 返回对象，可为空
     */
    private Object data;

    /**
     * 有实例时成功统一状态返回类型
     * @param data 返回对象
     * @return 统一返回对象
     */
    public static Result success(Object data) {
        return new Result(true, "00000", "success", data);
    }

    /**
     * 无示例时返回对象
     * @return 统一状态对象
     */
    public static Result success() {
        return new Result(true, "00000", "success", null);
    }

    /**
     * 错误状态返回对象
     * @param code 错误码
     * @param msg  错误信息
     * @return 错误状态返回对象
     */
    public static Result fail(String code, String msg) {
        return new Result(false, code, msg, null);
    }
}
