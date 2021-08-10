package com.young.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.domain.RoomType;
import com.young.service.RoomTypeService;
import com.young.utils.DataGridViewResult;
import com.young.vo.RoomTypeVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/admin/roomType")
@RestController
public class RoomTypeController {

    @Resource
    private RoomTypeService roomTypeService;

    /**
     * 分页查询所有房型
     * @param roomTypeVo 分页传参工具类
     * @return 回显信息
     */
    @RequestMapping("/findAllRoomTypeByPage")
    public DataGridViewResult findAllRoomTypeByPage(RoomTypeVo roomTypeVo){
        System.out.println("---------------------------");
        System.out.println(roomTypeVo.getTypeName());
        //将分页信息赋给分页助手
        PageHelper.startPage(roomTypeVo.getPage(),roomTypeVo.getLimit());
        //获取房型集合
        List<RoomType> roomTypeList = roomTypeService.findAllRoomTypeByPage(roomTypeVo);
        //将集合传给分页助手
        PageInfo<RoomType> pageInfo = new PageInfo<RoomType>(roomTypeList);
        //回显数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
}
