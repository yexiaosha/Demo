package com.yhdemo.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhdemo.demo.dao.OrderMapper;
import com.yhdemo.demo.pojo.Order;
import com.yhdemo.demo.pojo.PageData;
import com.yhdemo.demo.pojo.param.OrderParam;
import com.yhdemo.demo.pojo.param.PageParam;
import com.yhdemo.demo.pojo.vo.ErrorCode;
import com.yhdemo.demo.pojo.vo.OrderVo;
import com.yhdemo.demo.pojo.vo.Result;
import com.yhdemo.demo.service.OrderService;
import com.yhdemo.demo.utils.ResultUtils;
import com.yhdemo.demo.utils.UUIDUtils;
import com.yhdemo.demo.utils.aspects.SystemServiceLog;
import java.util.Date;
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
    public Result<Boolean> createOrder(OrderParam orderParam) {
        Order order = paramToOrder(orderParam);
        if (!orderMapper.insertOrder(order)) {
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        log.info("订单id：{}", order.getId());
        return ResultUtils.success();
    }

    @Override
    public Result<PageData<OrderVo>> findAllOrders(PageParam pageParam) {
        Page<OrderVo> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        return ResultUtils.success(new PageData<>(orderMapper.findAllOrders(page), 1L));
    }

    @Override
    public Result<PageData<OrderVo>> findAllOrdersByPage(int pageNum, int pageSize) {
        Page<OrderVo> page = new Page<>(pageNum, pageSize);
        return ResultUtils.success(new PageData<>(orderMapper.findAllOrderByPage(page).getRecords()));
    }


    public Order paramToOrder(OrderParam orderParam){
        Order order = new Order();
        order.setOrderName(orderParam.getOrderName());
        order.setUsername(orderParam.getUsername());
        order.setOrderContent(orderParam.getOrderContent());
        order.setTime(new Date());
        order.setOrderNumber(UUIDUtils.getUUID());
        return order;
    }
}
