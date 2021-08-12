package com.young.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.domain.Orders;
import com.young.service.AdminOrdersService;
import com.young.utils.DataGridViewResult;
import com.young.vo.OrdersVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/orders")
public class AdminOrdersController {

    @Resource
    private AdminOrdersService adminOrdersService;

    /**
     * 分页查询订单列表
     * @param ordersVo 订单传参工具类
     * @return 回显数据
     */
    @RequestMapping("/findOrdersByPage")
    public DataGridViewResult findOrdersByPage(OrdersVo ordersVo){
        //将分页信息交给分页助手
        PageHelper.startPage(ordersVo.getPage(),ordersVo.getLimit());
        //查询订单列表
        List<Orders> ordersList = adminOrdersService.findOrdersByPage(ordersVo);
        //将订单列表交给分页助手
        PageInfo<Orders> pageInfo = new PageInfo<Orders>(ordersList);
        //将数据回显
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

}
