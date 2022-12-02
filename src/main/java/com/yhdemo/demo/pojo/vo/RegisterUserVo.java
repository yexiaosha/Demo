package com.yhdemo.demo.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 注册用户信息返回视图类
 * @author wyh
 * @date 2022/11/22 10:02
 */

@Data
@ApiModel("用户注册信息视图类")
public class RegisterUserVo {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 注册时间
     */
    @ApiModelProperty("注册时间")
    private String registerTime;
    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private String birthday;
}
