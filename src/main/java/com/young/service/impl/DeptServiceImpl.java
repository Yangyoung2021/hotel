package com.young.service.impl;

import com.young.dao.DeptMapper;
import com.young.domain.Dept;
import com.young.service.DeptService;
import com.young.vo.DeptVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;


    /**
     * 查询部门列表
     * @param deptVo 传参工具类
     * @return 部门列表
     */
    public List<Dept> findDeptListByPage(DeptVo deptVo) {
        return deptMapper.findDeptListByPage(deptVo);
    }


    /**
     * 添加部门
     * @param dept 要添加的部门
     * @return 添加结果影响条数
     */
    public int addDept(Dept dept){
        //添加部门添加的时间
        dept.setCreateDate(new Date());
        return deptMapper.addDept(dept);
    }

    /**
     * 修改部门信息
     * @param dept 要修改的部门
     * @return 修改语句影响条数
     */
    public int updateDept(Dept dept) {
        //调用方法
        return deptMapper.updateDept(dept);
    }

    /**
     * 删除部门
     * @param depId 部门id
     * @return 影响条数
     */
    public Integer deleteDepById(Integer depId) {
        return deptMapper.deleteDeptById(depId);
    }

    /**
     * 查询所有部门为下拉列表传递数据
     * @return 部门列表
     */
    public List<Dept> findDeptList() {
        return deptMapper.findDeptList();
    }

}
