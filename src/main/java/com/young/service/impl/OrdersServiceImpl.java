package com.young.service.impl;

import com.young.dao.OrdersMapper;
import com.young.dao.RoomMapper;
import com.young.dao.RoomTypeMapper;
import com.young.domain.Orders;
import com.young.domain.Room;
import com.young.domain.RoomType;
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

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private RoomTypeMapper roomTypeMapper;

    /**
     * 添加订单
     * @param orders 要添加的订单
     * @return 添加结果
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public Integer addOrders(Orders orders) {
        orders.setStatus(1);//代表待确认
        orders.setOrdersno(UUIDUtils.UUIDRandom());
        orders.setReservedate(new Date());
        Integer count = ordersMapper.addOrders(orders);
        System.out.println("count的值为 ： "+ count);
        if (count > 0){
            //将当前房间状态修改为已预定
            Room room = new Room();
            room.setId(orders.getRoomid());
            room.setStatus(2);
            roomMapper.updateRoom(room);
            //将当前房型的已预定房间加一，空闲房间减一
            RoomType roomType = roomTypeMapper.findById(orders.getRoomtypeid());
            if ((roomType.getAvilablenum() - 1) < 0){
                //将房型状况改为住满
                roomType.setStatus(2);
            }else{
                //可用房型减一
                System.out.println("修改前可用房型数量为 : " +roomType.getAvilablenum());
                roomType.setAvilablenum(roomType.getAvilablenum() - 1);
                System.out.println("当前可用房型数量为 : " +roomType.getAvilablenum());
                //预定房型加一
                System.out.println("修改前预定房型数量为 : " +roomType.getReservednum());
                roomType.setReservednum(roomType.getReservednum() + 1);
                System.out.println("当前预定房型数量为 : " +roomType.getReservednum());
            }

            //更新房型
            roomTypeMapper.updateRoomType(roomType);
        }
        return count;
    }
}
