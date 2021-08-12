package com.young.dao;

import com.young.domain.Orders;
import com.young.vo.OrdersVo;

import java.util.List;

public interface AdminOrdersMapper {

    /**
     * 分页查询所有订单信息
     * @param ordersVo 订单信息传参类
     * @return 订单列表
     */
    List<Orders> findOrdersByPage(OrdersVo ordersVo);
}
