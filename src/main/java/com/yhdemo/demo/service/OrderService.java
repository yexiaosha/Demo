package com.yhdemo.demo.service;

import com.yhdemo.demo.pojo.param.OrderParam;
import com.yhdemo.demo.vo.Result;

/**
 * 订单业务接口
 * @author wyh
 * @date 2022/11/25 09:34
 */
public interface OrderService {

    /**
     * 生成订单
     * @param orderParam 订单参数
     * @return 通用返回结果
     */
    Result createOrder(OrderParam orderParam);

    /**
     * 分页查找
     * @param pageNum 分页查找
     * @param pageSize 分页查找
     * @return 通用返回结果
     */
    Result findAllOrderByUsername(int pageNum, int pageSize);

    /**
     * 分页查找
     * @param pageNum 分页查找
     * @param pageSize 分页查找
     * @return 通用返回结果
     */
    Result findAllOrders(int pageNum, int pageSize);
}
