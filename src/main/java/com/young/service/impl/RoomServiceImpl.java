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

    /**
     * 删除房间
     * @param id 房间id
     * @return 删除结果
     */
    public Integer deleteRoom(Integer id) {
        return roomMapper.deleteRoom(id);
    }

    /**
     * 根据楼层id查询房间
     * @param floorId 楼层id
     * @return 房间集合
     */
    public List<Room> findRoomByFloorId(Integer floorId) {
        return roomMapper.findRoomByFloorId(floorId);
    }

    /**
     * 根据房型id查询房间
     * @param roomTypeId 房型id
     * @return 房间集合
     */
    public List<Room> findRoomByTypeId(Integer roomTypeId) {
        return roomMapper.findRoomByTypeId(roomTypeId);
    }

    /**
     * 查询房间使用状态
     * @param id 房间id
     * @return 房间使用状态
     */
    public Integer checkStatus(Integer id) {
        return roomMapper.checkStatus(id);
    }

    /**
     * 查询所有房间
     * @return 房间集合
     */
    public List<Room> findAll() {
        return roomMapper.findAll();
    }

    /**
     * 查询房间详情
     * @param id 房间id
     * @return 查询的房间
     */
    public Room findByRoomId(Integer id) {
        return roomMapper.findByRoomId(id);
    }
}
