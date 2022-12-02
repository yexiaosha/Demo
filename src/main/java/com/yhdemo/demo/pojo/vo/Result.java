package com.yhdemo.demo.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("通用结果类")
public class Result<T> {

    /**
     * 返回事务状态
     */
    @ApiModelProperty("返回事务状态")
    private boolean success;
    /**
     * 状态码
     */
    @ApiModelProperty("状态码")
    private String code;
    /**
     * 状态信息
     */
    @ApiModelProperty("状态信息")
    private String msg;
    /**
     * 返回对象，可为空
     */
    @ApiModelProperty("返回对象")
    private T data;
}