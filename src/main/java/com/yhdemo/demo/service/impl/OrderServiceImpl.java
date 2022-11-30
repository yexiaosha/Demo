package com.yhdemo.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhdemo.demo.dao.OrderMapper;
import com.yhdemo.demo.pojo.Order;
import com.yhdemo.demo.pojo.param.OrderParam;
import com.yhdemo.demo.service.OrderService;
import com.yhdemo.demo.utils.DateUtils;
import com.yhdemo.demo.utils.UUIDUtils;
import com.yhdemo.demo.utils.aspects.SystemServiceLog;
import com.yhdemo.demo.vo.ErrorCode;
import com.yhdemo.demo.vo.OrderVo;
import com.yhdemo.demo.vo.Result;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 订单业务实现
 * @author wyh
 * @date 2022/11/25 09:34
 */

@Service
@Slf4j

public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    @SystemServiceLog("创建订单")
    public Result createOrder(OrderParam orderParam) {
        Order order = paramToOrder(orderParam);
        if (!orderMapper.insertOrder(order)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        log.info("订单id：{}",order.getId());
        return Result.success();
    }

    @Override
    public Result findAllOrderByUsername(int pageNum, int pageSize) {
        Page<OrderVo> page = new Page<>(pageNum, pageSize);
        return Result.success(orderMapper.findAllOrderByUsername(page).getRecords());
    }

    @Override
    public Result findAllOrders(int pageNum, int pageSize) {
        Page<OrderVo> page = new Page<>(pageNum, pageSize);
        return Result.success(orderMapper.findAllOrderByPage(page).getRecords());
    }


    public Order paramToOrder(OrderParam orderParam){
        Order order = new Order();
        order.setOrderName(orderParam.getOrderName());
        order.setUsername(orderParam.getUsername());
        order.setOrderContent(orderParam.getOrderContent());
        order.setTime(DateUtils.getPresentTime());
        order.setOrderNumber(UUIDUtils.getUUID());
        return order;
    }
}
