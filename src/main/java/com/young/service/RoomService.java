package com.young.service;

import com.young.domain.Room;
import com.young.vo.RoomVo;

import java.util.List;

public interface RoomService {

    /**
     * 分页查询房间集合
     * @param roomVo 房间传参工具类
     * @return 房间集合
     */
    List<Room> getRoomListByPage(RoomVo roomVo);

    /**
     * 添加房间
     * @param room 要添加的房间
     * @return 添加结果
     */
    Integer addRoom(Room room);

    /**
     * 修改房间
     * @param room 要修改的房间
     * @return 修改结果
     */
    Integer updateRoom(Room room);

    /**
     * 删除房间
     * @param id 房间id
     * @return 删除结果
     */
    Integer deleteRoom(Integer id);

    /**
     * 根据楼层id查询房间
     * @param floorId 楼层id
     * @return 房间集合
     */
    List<Room> findRoomByFloorId(Integer floorId);

    /**
     * 根据房型id查询房间
     * @param roomTypeId 房型id
     * @return 房间集合
     */
    List<Room> findRoomByTypeId(Integer roomTypeId);

    /**
     * 查询房间使用状态
     * @param id 房间id
     * @return 房间使用状态
     */
    Integer checkStatus(Integer id);

    /**
     * 查询所有房间
     * @return 房间集合
     */
    List<Room> findAll();

    /**
     * 查询房间详情
     * @param id 房间id
     * @return 查询的房间
     */
    Room findByRoomId(Integer id);
}
