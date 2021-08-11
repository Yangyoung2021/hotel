package com.young.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.domain.Employee;
import com.young.service.EmployeeService;
import com.young.service.RoleService;
import com.young.utils.DataGridViewResult;
import com.young.utils.SystemConstant;
import com.young.vo.EmployeeVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@RequestMapping("/admin/employee")
@RestController
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private RoleService roleService;

    /**
     * 员工登录
     * @param username 用户名
     * @param password 密码
     * @param session 存值域对象
     * @return json数据
     */
    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session){
        Map<String,Object> map = new HashMap<String, Object>();
        //调用业务层方法
        Employee employee = employeeService.login(username, password);
        //判断员工是否为空
        if (employee != null){
            //登陆成功，保存对象
            session.setAttribute(SystemConstant.LOGIN_USER,employee);
            map.put(SystemConstant.SUCCESS,true); //成功
        }else{
            map.put(SystemConstant.SUCCESS,false); //失败
            map.put(SystemConstant.MSG,"账号或密码错误");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 查询部门列表
     * @param employeeVo 传参工具类
     * @return 部门列表
     */
    @RequestMapping("/findAll")
    public DataGridViewResult list(EmployeeVo employeeVo){
        //设置分页信息
        PageHelper.startPage(employeeVo.getPage(),employeeVo.getLimit());
        //调用方法
        List<Employee> employeeList = employeeService.findAll(employeeVo);
        //创建分页对象
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(employeeList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加员工
     * @param employee 要添加的员工
     * @return 添加员工结果影响条数
     */
    @RequestMapping("/addEmployee")
    public String addEmployee(Employee employee,HttpSession session){
        //设置创建人
        Employee loginUser = (Employee) session.getAttribute(SystemConstant.LOGIN_USER);
        employee.setCreatedBy(loginUser.getId());
        //创建返回集合对象
        Map<String,Object> map = new HashMap<String, Object>();
        //判断执行结果
        if (employeeService.addEmployee(employee) > 0){
            //执行成功
            map.put(SystemConstant.SUCCESS,true);
            //返回信息
            map.put(SystemConstant.MSG,"添加成功");
        }else{
            //执行失败
            map.put(SystemConstant.SUCCESS,false);
            //返回信息
            map.put(SystemConstant.MSG,"添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 修改员工
     * @param employee 要修改的员工
     * @return 修改员工结果影响条数
     */
    @RequestMapping("/updateEmployee")
    public String updateEmployee(Employee employee,HttpSession session){
        //设置创建人
        Employee loginUser = (Employee) session.getAttribute(SystemConstant.LOGIN_USER);
        employee.setModifyBy(loginUser.getId());
        //创建返回集合对象
        Map<String,Object> map = new HashMap<String, Object>();
        //判断执行结果
        if (employeeService.updateEmployee(employee) > 0){
            //执行成功
            map.put(SystemConstant.SUCCESS,true);
            //返回信息
            map.put(SystemConstant.MSG,"修改成功");
        }else{
            //执行失败
            map.put(SystemConstant.SUCCESS,false);
            //返回信息
            map.put(SystemConstant.MSG,"修改失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 删除员工
     * @param eid 员工id
     * @return 删除结果影响条数
     */
    @RequestMapping("/deleteEmployee")
    public String deleteEmployee(Integer eid){
        //创建返回数据的聚合
        Map<String,Object> map = new HashMap<String, Object>();
        //判断删除结果
        if (employeeService.deleteEmployeeById(eid) > 0){
            //删除成功
            map.put(SystemConstant.SUCCESS,true);
            //回写数据
            map.put(SystemConstant.MSG,"删除成功");
        }else{
            //删除成功
            map.put(SystemConstant.SUCCESS,false);
            //回写数据
            map.put(SystemConstant.MSG,"删除失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 重置密码
     * @param eid 员工id
     * @return 删除结果影响条数
     */
    @RequestMapping("/resetPwd")
    public String resetPwd(Integer eid){
        //创建返回数据的聚合
        Map<String,Object> map = new HashMap<String, Object>();
        //判断删除结果
        if (employeeService.resetPwd(eid) > 0){
            //删除成功
            map.put(SystemConstant.SUCCESS,true);
            //回写数据
            map.put(SystemConstant.MSG,"重置成功");
        }else{
            //删除成功
            map.put(SystemConstant.SUCCESS,false);
            //回写数据
            map.put(SystemConstant.MSG,"重置失败");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/initRoleTable")
    public DataGridViewResult initRoleTable(Integer id){
        //查询当前员工拥有的角色集合
        Integer[] currentList = roleService.findRoleAndEmp(id);
        //调用方法查询所有角色集合
        List<Map<String, Object>> roleList = roleService.findRoleList();
        //遍历集合
        for (Map<String, Object> map : roleList) {
            //创建用于判断是否勾选的变量参数
            boolean LAY_CHECKED = false;
            int rid = (Integer) map.get("id");
            for (Integer role : currentList) {
                //判断吗属性值是否相等
                if (role.equals(rid)){
                    LAY_CHECKED = true;
                    break;
                }
            }
            map.put("LAY_CHECKED",LAY_CHECKED);
        }
        return new DataGridViewResult((long) roleList.size(),roleList);
    }

    /**
     * 保存选中的角色
     * @param roleIds 选中的角色
     * @param empId 员工id
     * @return 执行结果
     */
    @RequestMapping("/saveEmployee")
    public String saveEmployee(String roleIds,Integer empId){
        //创建集合回显数据
        Map<String,Object> map = new HashMap<String, Object>();
        //调用方法传递参数
        Integer index = employeeService.addMidTable(roleIds, empId);
        if (index > 0){
            //保存成功
            map.put(SystemConstant.SUCCESS,true);
            //提示信息
            map.put(SystemConstant.MSG,"保存成功");
        }else{
            //保存失败
            map.put(SystemConstant.SUCCESS,false);
            //提示信息
            map.put(SystemConstant.MSG,"保存失败");
        }
        return JSON.toJSONString(map);
    }


}
