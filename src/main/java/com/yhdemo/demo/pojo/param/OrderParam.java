package com.yhdemo.demo.pojo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 订单参数实体
 * @author wyh
 * @date 2022/11/25 09:35
 */

@Data
@ApiModel("订单参数类")
public class OrderParam {

    /**
     * 用户名
     */
    @NotBlank
    @Length(max = 30, min = 3, message = "用户名不能少于{min}个字符，不能大于{max}个字符")
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank
    @ApiModelProperty("订单名")
    private String orderName;

    @NotBlank
    @ApiModelProperty("订单内容")
    private String orderContent;

}
