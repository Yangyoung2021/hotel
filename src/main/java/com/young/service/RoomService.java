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
}