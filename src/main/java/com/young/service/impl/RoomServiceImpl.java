package com.young.service.impl;

import com.young.dao.RoomMapper;
import com.young.domain.Room;
import com.young.service.RoomService;
import com.young.vo.RoomVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Resource
    private RoomMapper roomMapper;

    /**
     * 分页查询房间
     * @param roomVo 房间传参工具类
     * @return 房间集合
     */
    public List<Room> getRoomListByPage(RoomVo roomVo) {
        return roomMapper.getRoomListByPage(roomVo);
    }

    /**
     * 添加房间
     * @param room 要添加的房间
     * @return 添加结果
     */
    public Integer addRoom(Room room) {
        return roomMapper.addRoom(room);
    }

    /**
     * 修改房间
     * @param room 要修改的房间
     * @return 修改结果
     */
    public Integer updateRoom(Room room) {
        return roomMapper.updateRoom(room);
    }
}
