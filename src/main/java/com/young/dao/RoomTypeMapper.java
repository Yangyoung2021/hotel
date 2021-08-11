package com.young.dao;

import com.young.domain.RoomType;
import com.young.vo.RoomTypeVo;

import java.util.List;

public interface RoomTypeMapper {

    /**
     * 分页查询所有房型
     * @param roomTypeVo 房型传参工具类
     * @return 房型集合
     */
    List<RoomType> findAllRoomTypeByPage(RoomTypeVo roomTypeVo);

    /**
     * 添加房型
     * @param roomType 要添加的房型
     * @return 添加结果
     */
    Integer addRoomType(RoomType roomType);

    /**
     * 修改房型
     * @param roomType 要修改的房型
     * @return 修改结果
     */
    Integer updateRoomType(RoomType roomType);

    /**
     * 空参查询所有房型
     * @return 房型集合
     */
    List<RoomType> findAll();

    /**
     * 根据id删除房型
     * @param id 房型id
     * @return 删除结果
     */
    Integer deleteRoomType(Integer id);
}
