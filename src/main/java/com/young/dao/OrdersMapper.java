package com.young.dao;

import com.young.domain.Orders;

public interface OrdersMapper {

    /**
     * 添加订单
     * @param orders 要添加的订单
     * @return 添加结果
     */
    Integer addOrders(Orders orders);
}
