package com.yhdemo.demo.pojo;

import java.util.Date;
import lombok.Data;

/**
 * 订单实体类
 * @author wyh
 * @date 2022/11/25 09:38
 */

@Data
public class Order {
    private Integer id;
    private String username;
    private String orderName;
    private Date time;
    private String orderContent;
    private String orderNumber;
}
