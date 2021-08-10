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
        return roomTypeMapper.addRoomType(roomType);
    }
}
