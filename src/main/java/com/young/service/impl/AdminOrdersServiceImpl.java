package com.young.service.impl;

import com.young.dao.AdminOrdersMapper;
import com.young.domain.Orders;
import com.young.service.AdminOrdersService;
import com.young.vo.OrdersVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class AdminOrdersServiceImpl implements AdminOrdersService {

    @Resource
    private AdminOrdersMapper adminOrdersMapper;

    /**
     * 分页查询所有订单信息
     * @param ordersVo 订单信息传参类
     * @return 订单列表
     */
    public List<Orders> findOrdersByPage(OrdersVo ordersVo) {
        return adminOrdersMapper.findOrdersByPage(ordersVo);
    }
}
