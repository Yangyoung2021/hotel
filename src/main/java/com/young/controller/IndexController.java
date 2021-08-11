package com.young.controller;

import com.young.domain.Floor;
import com.young.domain.RoomType;
import com.young.service.FloorService;
import com.young.service.RoomService;
import com.young.service.RoomTypeService;
import com.young.utils.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Resource
    private RoomTypeService roomTypeService;

    @Resource
    private FloorService floorService;

    @Resource
    private RoomService roomService;

    @RequestMapping("/homeIndex")
    public String index(HttpSession session){
        //获取所有房型
        List<RoomType> roomTypeList = roomTypeService.findAll();
        //将房型列表放入域对象
        session.setAttribute("roomTypeList",roomTypeList);
        //获取所有楼层信息
        List<Floor> floorList = floorService.findAll();
        //将楼层信息放入session域
        session.setAttribute("floorList",floorList);
        //获取所有房间

        return "forward:/home.jsp";
    }

}
