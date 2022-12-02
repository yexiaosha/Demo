package com.yhdemo.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 订单实体类
 * @author wyh
 * @date 2022/11/25 09:38
 */

@Data
@ApiModel("订单实体类")
public class Order {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("订单名称")
    private String orderName;

    @ApiModelProperty("订单生成时间")
    private Date time;

    @ApiModelProperty("订单内容")
    private String orderContent;

    @ApiModelProperty("订单号")
    private String orderNumber;
}
