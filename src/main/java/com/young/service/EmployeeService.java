package com.young.service;

import com.young.domain.Employee;
import com.young.domain.Role;
import com.young.vo.EmployeeVo;

import java.util.List;

public interface EmployeeService {

    /**
     * 员工登录
     * @param loginName 登录名称
     * @param loginPwd 登陆密码
     * @return 员工对象
     */
    Employee login(String loginName,String loginPwd);

    /**
     * 查询当前部门是否有员工
     * @param deptId 部门id
     * @return 员工数量
     */
    int findEmpByDepId(Integer deptId);


    /**
     * 根据员工id查询是否存在员工
     * @param roleId 员工id
     * @return 员工数量
     */
    int findEmployeeByRoleId(Integer roleId);

    /**
     * 查询所有员工
     * @return 员工集合
     */
    List<Employee> findAll(EmployeeVo employeeVo);

    /**
     * 添加员工
     * @param employee 要添加的员工
     * @return 添加结果影响条数
     */
    Integer addEmployee(Employee employee);

    /**
     * 修改员工
     * @param employee 要修改的员工
     * @return 修改结果
     */
    Integer updateEmployee(Employee employee);

    /**
     * 删除员工
     * @param id 员工id
     * @return 删除结果
     */
    Integer deleteEmployeeById(Integer id);

    /**
     * 重置密码
     * @param eid 员工id
     * @return 重置结果
     */
    Integer resetPwd(Integer eid);

    /**
     * 保存角色员工关系
     * @param ids 角色id组
     * @param eid 员工id
     * @return 执行结果
     */
    Integer addMidTable(String ids,Integer eid);
}
