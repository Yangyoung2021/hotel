package com.young.service.impl;

import com.young.dao.OrdersMapper;
import com.young.domain.Orders;
import com.young.service.OrdersService;
import com.young.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private OrdersMapper ordersMapper;

    /**
     * 添加订单
     * @param orders 要添加的订单
     * @return 添加结果
     */
    public Integer addOrders(Orders orders) {
        orders.setStatus(1);//代表待确认
        orders.setOrdersno(UUIDUtils.UUIDRandom());
        orders.setReservedate(new Date());
        return ordersMapper.addOrders(orders);
    }
}
