package com.yhdemo.demo.pojo.vo;

import com.yhdemo.demo.pojo.Order;
import com.yhdemo.demo.pojo.SexEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * 订单表视图类
 * @author wyh
 * @date 2022/11/25 09:40
 */

@Data
@ApiModel("订单视图类")
public class OrderVo {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("性别")
    private SexEnum gender;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("订单列表")
    private List<Order> orderList;

}
