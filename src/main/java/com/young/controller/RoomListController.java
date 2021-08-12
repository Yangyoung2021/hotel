package com.young.controller;

import com.young.domain.Floor;
import com.young.domain.Room;
import com.young.domain.RoomType;
import com.young.service.FloorService;
import com.young.service.RoomService;
import com.young.service.RoomTypeService;
import com.young.vo.RoomVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/hotelList")
public class RoomListController {

    @Resource
    private RoomService roomService;

    @Resource
    private RoomTypeService roomTypeService;

    @Resource
    private FloorService floorService;

    /**
     * 去到酒店房间列表
     * @return 酒店房间列表
     */
    @RequestMapping("/toRoomList")
    public String toRoomList(Model model){
        //创建传参类对象
        RoomVo roomVo = new RoomVo();
        roomVo.setStatus(1);
        //调用查询所有房间方法
        List<RoomType> roomTypeList = roomTypeService.findAllRoomTypeByPage(null);
        //将房型列表传给模型对象
        model.addAttribute("roomTypeList",roomTypeList);
        //调用查询所有房间方法
        List<Room> roomList = roomService.getRoomListByPage(roomVo);
        //将房间列表传给模型对象
        model.addAttribute("roomList",roomList);
        //调用查询楼层方法查询楼层列表
        List<Floor> floorList = floorService.findFloorListByPage(null);
        //将楼层列表传给模型对象
        model.addAttribute("floorList",floorList);
        return "hotelList";
    }

    /**
     * 根据房型查询去到酒店房间列表
     * @return 酒店房间列表
     */
    @RequestMapping(value = {"/toRoomListById/{roomTypeId}"})
    public String toRoomListById(@PathVariable("roomTypeId")Integer roomTypeId,
                             Model model){
        //创建传参类对象
        RoomVo roomVo = new RoomVo();
        roomVo.setRoomtypeid(roomTypeId);
        //设置只能显示可预订
        roomVo.setStatus(1);
        roomVo.setRoomtypeid(roomTypeId);
        //调用查询所有房型方法
        List<RoomType> roomTypeList = roomTypeService.findAllRoomTypeByPage(null);
        //将房型列表传给模型对象
        model.addAttribute("roomTypeList",roomTypeList);
        //调用查询楼层方法查询楼层列表
        List<Floor> floorList = floorService.findFloorListByPage(null);
        //将楼层列表传给模型对象
        model.addAttribute("floorList",floorList);
        //将当前房型id传递给模型对象
        model.addAttribute("roomTypeId",roomTypeId);


        //调用查询所有房间方法
        List<Room> roomList = roomService.getRoomListByPage(roomVo);
        //将房间列表传给模型对象
        model.addAttribute("roomList",roomList);
        return "hotelList";
    }

}
