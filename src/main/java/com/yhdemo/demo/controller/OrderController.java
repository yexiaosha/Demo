package com.yhdemo.demo.controller;

import com.yhdemo.demo.pojo.PageData;
import com.yhdemo.demo.pojo.param.OrderParam;
import com.yhdemo.demo.pojo.param.PageParam;
import com.yhdemo.demo.pojo.vo.OrderVo;
import com.yhdemo.demo.pojo.vo.Result;
import com.yhdemo.demo.service.OrderService;
import com.yhdemo.demo.utils.aspects.SystemControllerLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(tags = "订单操作")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    @SystemControllerLog("记录订单信息")
    @ApiOperation(value = "记录订单信息", notes = "返回是否记录成功")
    public Result<Boolean> createOrder(@RequestBody OrderParam orderParam) {
        return orderService.createOrder(orderParam);
    }

    @GetMapping("/findAll")
    @SystemControllerLog("根据用户名分页查找订单")
    @ApiOperation(value = "根据用户名分页查找订单", notes = "返回分页对象，包含查找的对象的对象信息")
    public Result<PageData<OrderVo>> findOrderList(@RequestBody PageParam pageParam) {
        return orderService.findAllOrders(pageParam);
    }
}
