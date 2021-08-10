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
}
