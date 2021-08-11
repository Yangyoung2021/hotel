package com.young.service.impl;

import com.young.dao.RoomTypeMapper;
import com.young.domain.RoomType;
import com.young.service.RoomTypeService;
import com.young.vo.RoomTypeVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {

    @Resource
    private RoomTypeMapper roomTypeMapper;

    /**
     * 分页查询房型集合
     * @param roomTypeVo 房型传参工具类
     * @return 房型集合
     */
    public List<RoomType> findAllRoomTypeByPage(RoomTypeVo roomTypeVo) {
        return roomTypeMapper.findAllRoomTypeByPage(roomTypeVo);
    }

    /**
     * 添加房型
     * @param roomType 要添加的房型
     * @return 添加结果
     */
    public Integer addRoomType(RoomType roomType) {
        //可用房间数默认是全部房间数量
        roomType.setAvilablenum(roomType.getRoomnum());
        //设置已入住数量为0
        roomType.setLivednum(0);
        return roomTypeMapper.addRoomType(roomType);
    }

    /**
     * 修改房型
     * @param roomType 要修改的房型
     * @return 修改结果
     */
    public Integer updateRoomType(RoomType roomType) {
        return roomTypeMapper.updateRoomType(roomType);
    }

    /**
     * 空参查询所有房型
     * @return 房型集合
     */
    public List<RoomType> findAll() {
        return roomTypeMapper.findAll();
    }
}
