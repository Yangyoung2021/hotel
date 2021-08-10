package com.young.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.domain.Dept;
import com.young.service.DeptService;
import com.young.service.EmployeeService;
import com.young.utils.DataGridViewResult;
import com.young.utils.SystemConstant;
import com.young.vo.DeptVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @Resource
    private EmployeeService employeeService;

    /**
     * 查询部门列表
     * @param deptVo 传参工具类
     * @return 部门列表
     */
    @RequestMapping("/list")
    public DataGridViewResult list(DeptVo deptVo){
        //设置分页信息
        PageHelper.startPage(deptVo.getPage(),deptVo.getLimit());
        //调用方法
        List<Dept> deptList = deptService.findDeptListByPage(deptVo);
        //创建分页对象
        PageInfo<Dept> pageInfo = new PageInfo<Dept>(deptList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加部门
     * @param dept 要添加的部门
     * @return 添加结果影响条数
     */
    @RequestMapping("/addDept")
    public String addDept(Dept dept){
        //新建返回集合
        Map<String,Object> map = new HashMap<String, Object>();
        //调用方法
        int index = deptService.addDept(dept);
        //判断添加的成功与否
        if (index > 0){
            //添加成功
            map.put(SystemConstant.SUCCESS,true);
            //返回信息
            map.put(SystemConstant.MSG,"添加成功");
        }else{
            //添加失败
            map.put(SystemConstant.SUCCESS,false);
            //返回信息
            map.put(SystemConstant.MSG,"添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 修改部门
     * @param dept 要修改的部门
     * @return 添加结果影响条数
     */
    @RequestMapping("/updateDept")
    public String updateDept(Dept dept){
        //新建返回集合
        Map<String,Object> map = new HashMap<String, Object>();
        //调用方法
        int index = deptService.updateDept(dept);
        //判断添加的成功与否
        if (index > 0){
            //添加成功
            map.put(SystemConstant.SUCCESS,true);
            //返回信息
            map.put(SystemConstant.MSG,"修改成功");
        }else{
            //添加失败
            map.put(SystemConstant.SUCCESS,false);
            //返回信息
            map.put(SystemConstant.MSG,"修改失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 查询当前部门是否存在员工
     * @param deptId 部门id
     * @return 提示信息
     */
    @RequestMapping("/checkDeptEmployee")
    public String checkDeptEmployee(Integer deptId){
        //创建map集合用于回显数据
        Map<String,Object> map = new HashMap<String, Object>();
        //调用业务层方法
        int num = employeeService.findEmpByDepId(deptId);
        //判断部门员工数量
        if (num > 0){
            //部门还有人
            map.put(SystemConstant.EXIST,true);
            //提示信息
            map.put(SystemConstant.MSG,"当前部门存在员工，不可删除");
        }else{
            //部门没人了
            map.put(SystemConstant.EXIST,false);
        }
        return JSON.toJSONString(map);
    }

    /**
     * 删除部门
     * @param deptId 部门id
     * @return 回显数据
     */
    @RequestMapping("/deleteDept")
    public String deleteDept(Integer deptId){
        //创建map集合用于回显数据
        Map<String,Object> map = new HashMap<String, Object>();
        //判断是否删除成功
        if (deptService.deleteDepById(deptId) > 0){
            //删除成功
            map.put(SystemConstant.SUCCESS,true);
            //提示信息
            map.put(SystemConstant.MSG,"删除成功");
        }else{
            //删除失败
            map.put(SystemConstant.SUCCESS,false);
            //提示信息
            map.put(SystemConstant.MSG,"删除失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 查询部门列表
     * @return 部门列表
     */
    @RequestMapping("/deptList")
    public String deptList(){
        return JSON.toJSONString(deptService.findDeptList());
    }
}
