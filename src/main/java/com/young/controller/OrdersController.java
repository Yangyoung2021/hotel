package com.young.controller;

import com.alibaba.fastjson.JSON;
import com.young.domain.Orders;
import com.young.service.OrdersService;
import com.young.utils.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/orders")
@Controller
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    /**
     * 添加订单
     * @param orders 要添加的订单
     * @return 回显数据
     */
    @RequestMapping("/addOrders")
    @ResponseBody
    public String addOrders(Orders orders){
        //创建回显集合
        Map<String,Object> map = new HashMap<String, Object>();
        //调用方法判断结果
        if (ordersService.addOrders(orders) > 0){
            //添加成功
            map.put(SystemConstant.SUCCESS,true);
            //回显信息
            map.put(SystemConstant.MSG,"预定成功");
        }else{
            //添加失败
            map.put(SystemConstant.SUCCESS,false);
            //回显信息
            map.put(SystemConstant.MSG,"预定失败，请稍后重试！");
        }
        return JSON.toJSONString(map);
    }

}
