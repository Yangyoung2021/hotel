package com.young.service;

import com.young.domain.Dept;
import com.young.vo.DeptVo;

import java.util.List;

public interface DeptService {

    /**
     * 查询部门列表
     * @param deptVo 传参工具类
     * @return 部门列表
     */
    List<Dept> findDeptListByPage(DeptVo deptVo);

    /**
     * 添加部门
     * @param dept 要添加的部门
     * @return 添加结果返回影响的条数
     */
    int addDept(Dept dept);

    /**
     * 修改部门信息
     * @param dept 要修改的部门
     * @return 修改语句后执行结果影响的条数
     */
    int updateDept(Dept dept);


    /**
     * 删除部门
     * @param depId 部门id
     * @return 影响条数
     */
    Integer deleteDepById(Integer depId);

    /**
     * 查询所有部门列表
     * @return 部门列表
     */
    List<Dept> findDeptList();

}
