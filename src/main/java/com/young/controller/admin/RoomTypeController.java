package com.young.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.domain.RoomType;
import com.young.service.RoomTypeService;
import com.young.utils.DataGridViewResult;
import com.young.utils.SystemConstant;
import com.young.utils.UUIDUtils;
import com.young.vo.RoomTypeVo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
