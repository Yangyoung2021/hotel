package com.young.controller;

import com.young.domain.Room;
import com.young.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/detail")
public class RoomDetailController {

    @Resource
    private RoomService roomService;

    @RequestMapping("/roomDetail/{id}")
    public String roomDetail(@PathVariable("id")Integer id, Model model){
        //创建返回数据的集合
        Map<String,Object> map = new HashMap<String, Object>();
        //根据房间id查询当前的唯一房间信息
        Room detailRoom = roomService.findByRoomId(id);
        //将房间信息放入模型
        model.addAttribute("room",detailRoom);
        return "detail";
    }

}
