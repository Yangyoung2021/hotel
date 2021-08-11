package com.young.service.impl;

import com.young.dao.FloorMapper;
import com.young.domain.Floor;
import com.young.service.FloorService;
import com.young.vo.FloorVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class FloorServiceImpl implements FloorService {

    @Resource
    private FloorMapper floorMapper;

    /**
     * 查询所有楼层信息
     * @param floorVo 楼层传参工具类
     * @return 楼层集合
     */
    public List<Floor> findFloorListByPage(FloorVo floorVo) {
        return floorMapper.findFloorListByPage(floorVo);
    }

    /**
     * 添加楼层
     * @param floor 要添加的楼层
     * @return 添加楼层方法影响的条数
     */
    public Integer addFloor(Floor floor) {
        return floorMapper.addFloor(floor);
    }

    /**
     * 删除楼层信息
     * @param id 楼层id
     * @return 删除结果
     */
    public Integer deleteFloor(Integer id) {
        return floorMapper.deleteFloor(id);
    }

    /**
     * 修改楼层信息
     * @param floor 要修改的楼层
     * @return 修改结果
     */
    public Integer updateFloor(Floor floor) {
        return floorMapper.updateFloor(floor);
    }

    /**
     * 空参查询所有楼层集合
     * @return 楼层集合
     */
    public List<Floor> findAll() {
        return floorMapper.findAll();
    }
}
