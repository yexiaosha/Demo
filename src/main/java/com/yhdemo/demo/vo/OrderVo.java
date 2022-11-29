package com.yhdemo.demo.vo;

import com.yhdemo.demo.pojo.Order;
import com.yhdemo.demo.pojo.SexEnum;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * 订单表视图类
 * @author wyh
 * @date 2022/11/25 09:40
 */

@Data
public class OrderVo {
    private String username;
    private String email;
    private SexEnum gender;
    private Date birthday;
    private List<Order> orderList;

}
