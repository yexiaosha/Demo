package com.yhdemo.demo.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhdemo.demo.pojo.Order;
import com.yhdemo.demo.pojo.vo.OrderVo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 订单接口
 * @author wyh
 * @date 2022/11/25 09:51
 */

@Mapper
public interface OrderMapper {

    /**
     * 插入订单
     * @param order 订单实体
     * @return 是否正确完成订单插入
     */
    boolean insertOrder(@Param("order") Order order);

    /**
     *
     * @return
     */
    IPage<OrderVo> findAllOrderByPage(Page<OrderVo> page);

    /**
     * 分页查找用户所有订单
     * @param page 用户名
     * @return 订单列表
     */
    IPage<OrderVo> findAllOrders(Page<OrderVo> page);

    /**
     *
     * @param username
     * @return
     */
    List<Order> findOrderMap(String username);
}
