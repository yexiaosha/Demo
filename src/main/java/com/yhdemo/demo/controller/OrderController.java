package com.yhdemo.demo.controller;

import com.yhdemo.demo.pojo.param.OrderParam;
import com.yhdemo.demo.service.OrderService;
import com.yhdemo.demo.utils.aspects.SystemControllerLog;
import com.yhdemo.demo.vo.Result;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单接口
 * @author wyh
 * @date 2022/11/25 09:33
 */

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    @SystemControllerLog("生成订单")
    public Result createOrder(@RequestBody OrderParam orderParam){
        return orderService.createOrder(orderParam);
    }

    @GetMapping("/findAll")
    @SystemControllerLog("根据用户名分页查找用户")
    public Result findOrderListByUsername(@RequestHeader @NotNull int pageNum, @RequestHeader @NotNull int pageSize){
        return orderService.findAllOrderByUsername(pageNum, pageSize);
    }
}
