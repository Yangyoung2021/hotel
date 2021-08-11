package com.young.controller.admin;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.domain.Floor;
import com.young.service.FloorService;
import com.young.utils.DataGridViewResult;
import com.young.utils.SystemConstant;
import com.young.vo.FloorVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin/floor")
@RestController
public class FloorController {

    @Resource
    private FloorService floorService;

    /**
     * 查询所有楼层信息
     * @param floorVo 楼层传参工具类
     * @return 楼层集合
     */
    @RequestMapping("/findFloorListByPage")
    public DataGridViewResult findFloorListByPage(FloorVo floorVo){
        //将分页信息传给分页助手
        PageHelper.startPage(floorVo.getPage(),floorVo.getLimit());
        //调用方法查询所有楼层信息
        List<Floor> floorList = floorService.findFloorListByPage(floorVo);
        //将数据传给分页助手
        PageInfo<Floor> pageInfo = new PageInfo<Floor>(floorList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加员工
     * @param floor 要添加的员工
     * @return 添加员工结果影响条数
     */
    @RequestMapping("/addFloor")
    public String addFloor(Floor floor){
        //创建返回集合对象
        Map<String,Object> map = new HashMap<String, Object>();
        //判断执行结果
        if (floorService.addFloor(floor) > 0){
            //执行成功
            map.put(SystemConstant.SUCCESS,true);
            //返回信息
            map.put(SystemConstant.MSG,"添加成功");
        }else{
            //执行失败
            map.put(SystemConstant.SUCCESS,false);
            //返回信息
            map.put(SystemConstant.MSG,"添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 更新楼层信息
     * @param floor 要更新的楼层
     * @return 更新结果
     */
    @RequestMapping("/updateFloor")
    public String updateFloor(Floor floor){
        //创建返回数据的map集合
        Map<String,Object> map = new HashMap<String, Object>();
        //调用上层方法判断是否成功
        if (floorService.updateFloor(floor) > 0){
            //成功
            map.put(SystemConstant.SUCCESS,true);
            //返回信息
            map.put(SystemConstant.MSG,"修改成功");
        }else {
            //失败
            map.put(SystemConstant.SUCCESS,false);
            //返回信息
            map.put(SystemConstant.MSG,"修改失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 删除楼层信息
     * @param id 要删除的楼层id
     * @return 删除结果
     */
    @RequestMapping("/deleteFloor")
    public String deleteFloor(Integer id){
        //创建返回数据的map集合
        Map<String,Object> map = new HashMap<String, Object>();
        //调用上层方法判断是否成功
        if (floorService.deleteFloor(id) > 0){
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

    /**
     * 空参查询所有楼层集合
     * @return json数据
     */
    @RequestMapping("/findAll")
    public String findAll(){
        return JSON.toJSONString(floorService.findAll());
    }

}
