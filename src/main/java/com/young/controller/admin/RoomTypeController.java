package com.young.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.domain.RoomType;
import com.young.service.RoomService;
import com.young.service.RoomTypeService;
import com.young.utils.DataGridViewResult;
import com.young.utils.SystemConstant;
import com.young.vo.RoomTypeVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin/roomType")
@RestController
public class RoomTypeController {

    @Resource
    private RoomTypeService roomTypeService;

    @Resource
    private RoomService roomService;

    /**
     * 分页查询所有房型
     * @param roomTypeVo 分页传参工具类
     * @return 回显信息
     */
    @RequestMapping("/findAllRoomTypeByPage")
    public DataGridViewResult findAllRoomTypeByPage(RoomTypeVo roomTypeVo){
        //将分页信息赋给分页助手
        PageHelper.startPage(roomTypeVo.getPage(),roomTypeVo.getLimit());
        //获取房型集合
        List<RoomType> roomTypeList = roomTypeService.findAllRoomTypeByPage(roomTypeVo);
        //将集合传给分页助手
        PageInfo<RoomType> pageInfo = new PageInfo<RoomType>(roomTypeList);
        //回显数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }


    /**
     * 添加房型
     * @param roomType 要添加的房型
     * @return 回显信息
     */
    @RequestMapping("/addRoomType")
    public String addRoomType(RoomType roomType){
        //创建回显信息用的集合
        Map<String,Object> map = new HashMap<String, Object>();
        //判段结果
        if (roomTypeService.addRoomType(roomType) > 0){
            //添加成功
            map.put(SystemConstant.SUCCESS,true);
            //返回信息
            map.put(SystemConstant.MSG,"添加成功");
        }else{
            //添加失败
            map.put(SystemConstant.SUCCESS,false);
            //返回信息
            map.put(SystemConstant.MSG,"添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 修改房型
     * @param roomType 要修改的房型
     * @return 回显信息
     */
    @RequestMapping("/updateRoomType")
    public String updateRoomType(RoomType roomType){
        //创建回显信息用的集合
        Map<String,Object> map = new HashMap<String, Object>();
        //判段结果
        if (roomTypeService.updateRoomType(roomType) > 0){
            //添加成功
            map.put(SystemConstant.SUCCESS,true);
            //返回信息
            map.put(SystemConstant.MSG,"修改成功");
        }else{
            //添加失败
            map.put(SystemConstant.SUCCESS,false);
            //返回信息
            map.put(SystemConstant.MSG,"修改失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 空参查询所有房型
     * @return json数据
     */
    @RequestMapping("/findAll")
    public String findAll(){
        return JSON.toJSONString(roomTypeService.findAll());
    }

    /**
     * 根据房型id查询房间
     * @param id 房型id
     * @return 回显数据
     */
    @RequestMapping("/checkExistRoom")
    public String checkExistRoom(Integer id){
        //创建返回数据的集合
        Map<String,Object> map = new HashMap<String, Object>();
        //调用方法判断结果
        if (roomService.findRoomByTypeId(id).size() > 0){
            //存在房间
            map.put(SystemConstant.EXIST,true);
            //提示用户不能删除
            map.put(SystemConstant.MSG,"当前房型存在房间，不能删除");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 根据id删除房型
     * @param id 房型id
     * @return 回显信息
     */
    @RequestMapping("/deleteRoomType")
    public String deleteRoomType(Integer id){
        //创建回显信息集合
        Map<String,Object> map = new HashMap<String, Object>();
        //判断结果
        if (roomTypeService.deleteRoomType(id) > 0){
            //成功
            map.put(SystemConstant.SUCCESS,true);
            //返回信息
            map.put(SystemConstant.MSG,"删除成功");
        }else {
            //失败
            map.put(SystemConstant.SUCCESS,false);
            //返回信息
            map.put(SystemConstant.MSG,"删除失败");
        }
        return JSON.toJSONString(map);
    }

}
