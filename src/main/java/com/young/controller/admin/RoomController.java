package com.young.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.domain.Room;
import com.young.service.RoomService;
import com.young.utils.DataGridViewResult;
import com.young.utils.SystemConstant;
import com.young.vo.RoomVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 添加房间
     * @param room 要添加的房间
     * @return 回显数据
     */
    @RequestMapping("/addRoom")
    public String addRoom(Room room){
        //创建返回数据的集合
        Map<String,Object> map = new HashMap<String, Object>();
        //调用方法判断数据
        if (roomService.addRoom(room) > 0){
            //添加成功
            map.put(SystemConstant.SUCCESS,true);
            //返回信息
            map.put(SystemConstant.MSG,"添加成功");
        }else{
            //添加成功
            map.put(SystemConstant.SUCCESS,false);
            //返回信息
            map.put(SystemConstant.MSG,"添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 修改房间
     * @param room 要修改的房间
     * @return 回显数据
     */
    @RequestMapping("/updateRoom")
    public String updateRoom(Room room){
        //创建返回数据的集合
        Map<String,Object> map = new HashMap<String, Object>();
        //调用方法判断数据
        if (roomService.updateRoom(room) > 0){
            //添加成功
            map.put(SystemConstant.SUCCESS,true);
            //返回信息
            map.put(SystemConstant.MSG,"修改成功");
        }else{
            //添加成功
            map.put(SystemConstant.SUCCESS,false);
            //返回信息
            map.put(SystemConstant.MSG,"修改失败");
        }
        return JSON.toJSONString(map);
    }

}
