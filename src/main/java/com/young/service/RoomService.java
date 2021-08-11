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
}
