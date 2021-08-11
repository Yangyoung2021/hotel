package com.young.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.domain.Room;
import com.young.service.RoomService;
import com.young.utils.DataGridViewResult;
import com.young.vo.RoomVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/admin/room")
@RestController
public class RoomController {

    @Resource
    private RoomService roomService;

    /**
     * 分页查询房间
     * @param roomVo 房间传参工具类
     * @return 房间集合
     */
    @RequestMapping("/getRoomListByPage")
    public DataGridViewResult getRoomListByPage(RoomVo roomVo){
        //将分页参数传递给分页助手
        PageHelper.startPage(roomVo.getPage(),roomVo.getLimit());
        //查询获取房间集合
        List<Room> roomListByPage = roomService.getRoomListByPage(roomVo);
        //将房间集合传递给分页助手
        PageInfo<Room> pageInfo = new PageInfo<Room>(roomListByPage);
        //返回房间集合
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
}
