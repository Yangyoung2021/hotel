package com.young.dao;

import com.young.domain.Floor;
import com.young.vo.FloorVo;

import java.util.List;

public interface FloorMapper {

    /**
     * 查询所有楼层
     * @param floorVo 楼层传参工具类
     * @return 楼层集合
     */
    public List<Floor> findFloorListByPage(FloorVo floorVo);

    /**
     * 添加楼层
     * @param floor 要添加的楼层
     * @return 添加结果
     */
    Integer addFloor(Floor floor);

    /**
     * 根据id删除楼层信息
     * @param id 楼层id
     * @return 删除结果
     */
    Integer deleteFloor(Integer id);

    /**
     * 修改楼层信息
     * @param floor 要修改的楼层
     * @return 修改结果
     */
    Integer updateFloor(Floor floor);

    /**
     * 空参查询所有楼层集合
     * @return 楼层集合
     */
    List<Floor> findAll();
}
