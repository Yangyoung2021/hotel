package com.young.dao;

import com.young.domain.Employee;
import com.young.vo.EmployeeVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {

    /**
     * 根据登录名查找员工信息
     * @param loginName 登录名
     * @return 员工
     */
    Employee findEmployeeByLoginName(String loginName);


    /**
     * 根据部门id查询是否存在员工
     * @param deptId 部门id
     * @return 员工数量
     */
    int findEmployeeByDeptId(Integer deptId);

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
     * @param employee 修改员工
     * @return 修改结果
     */
    Integer modifyEmployee(Employee employee);

    /**
     * 删除员工
     * @param id 员工id
     * @return 删除结果
     */
    Integer deleteEmployeeById(Integer id);

    /**
     * 删除中间表数据
     * @param eid 员工id
     * @return 删除结果
     */
    Integer deleteMidTable(Integer eid);

    /**
     * 添加中间表数据
     * @param rid 角色id
     * @param eid 员工id
     */
    @Insert("insert into sys_role_employee (rid,eid) values (#{rid},#{eid})")
    void addMidTable(@Param("rid") String rid,@Param("eid") Integer eid);
}
