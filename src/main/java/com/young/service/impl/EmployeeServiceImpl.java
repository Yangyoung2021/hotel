package com.young.service.impl;

import com.young.dao.EmployeeMapper;
import com.young.domain.Employee;
import com.young.service.EmployeeService;
import com.young.utils.PasswordUtil;
import com.young.utils.SystemConstant;
import com.young.utils.UUIDUtils;
import com.young.vo.EmployeeVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     * @param loginName 登录名称
     * @param loginPwd 登陆密码
     * @return 员工对象
     */
    public Employee login(String loginName, String loginPwd) {
//        EmployeeMapper employeeMapper = getEmployeeMapper();
        //调用根据用户名查询用户的方法
        Employee employee = employeeMapper.findEmployeeByLoginName(loginName);
        //判断对象是否为空
        if(employee != null){
            //将密码进行加密处理
            String newPassword = PasswordUtil.md5(loginPwd, employee.getSalt(), SystemConstant.PASSWORD_COUNT);
            //判断密码是否一致
            if (employee.getLoginPwd().equals(newPassword)){
                //登陆成功
                return employee;
            }
        }
        //登陆失败！！
        return null;
    }

    /**
     * 查询当前部门是否有员工
     * @param deptId 部门id
     * @return 员工数量
     */
    public int findEmpByDepId(Integer deptId){
        return employeeMapper.findEmployeeByDeptId(deptId);
    }

    /**
     * 查询当前部门是否有员工
     * @param roleId 员工id
     * @return 员工数量
     */
    public int findEmployeeByRoleId(Integer roleId) {
        return employeeMapper.findEmployeeByRoleId(roleId);
    }

    /**
     * 查询所有员工
     * @return 员工集合
     */
    public List<Employee> findAll(EmployeeVo employeeVo) {
        return employeeMapper.findAll(employeeVo);
    }

    /**
     * 添加员工
     * @param employee 要添加的员工
     * @return 添加结果影响条数
     */
    public Integer addEmployee(Employee employee) {
        //设置盐值
        employee.setSalt(UUIDUtils.UUIDRandom());
        //将时间和密码加入
        employee.setCreateDate(new Date());
        //将密码加密
        employee.setLoginPwd(PasswordUtil.md5(SystemConstant.DEFAULT_PWD,employee.getSalt(),SystemConstant.PASSWORD_COUNT));
        //调用方法返回
        return employeeMapper.addEmployee(employee);
    }

    /**
     * 修改员工
     * @param employee 要修改的员工
     * @return 修改结果
     */
    public Integer updateEmployee(Employee employee) {
        //设置修改日期
        employee.setModifyDate(new Date());
        //调用方法返回
        return employeeMapper.modifyEmployee(employee);
    }

    /**
     * 删除员工
     * @param id 员工id
     * @return 删除结果
     */
    public Integer deleteEmployeeById(Integer id) {
        //删除中间关系数据表
        Integer index = employeeMapper.deleteMidTable(id);
        if (index > 0){
            System.out.println("删除中间关系表成功");
        }else{
            System.out.println("中间关系表删除失败");
        }
        return employeeMapper.deleteEmployeeById(id);
    }

    /**
     * 重置密码
     * @param eid 员工id
     * @return 重置结果
     */
    public Integer resetPwd(Integer eid) {
        Employee employee = new Employee();
        //重置盐值
        employee.setSalt(UUIDUtils.UUIDRandom());//重置盐值
        //重置密码
        employee.setLoginPwd(PasswordUtil.md5(SystemConstant.DEFAULT_PWD,employee.getSalt(),SystemConstant.PASSWORD_COUNT));
        //传入id
        employee.setId(eid);
        System.out.println(employee);
        //修改数据
        return employeeMapper.modifyEmployee(employee);
    }

    /**
     * 保存角色员工关系
     * @param ids 角色id组
     * @param eid 员工id
     * @return 执行结果
     */
    public Integer addMidTable(String ids, Integer eid) {
        try {
            //将原有的员工的角色删除
            Integer index = employeeMapper.deleteMidTable(eid);
            if(index > 0 ){
                System.out.println("原有角色关系删除成功");
            }else{
                System.out.println("原有角色关系删除失败");
            }
            //将角色字符串转成数组
            String[] idStr = ids.split(",");
            //遍历数组
            for (String rid : idStr) {
                employeeMapper.addMidTable(rid,eid);
            }
            //表示成功
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
}
