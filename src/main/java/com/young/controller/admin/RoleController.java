package com.young.controller.admin;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.domain.Menu;
import com.young.domain.Role;
import com.young.service.EmployeeService;
import com.young.service.MenuService;
import com.young.service.RoleService;
import com.young.utils.DataGridViewResult;
import com.young.utils.SystemConstant;
import com.young.utils.TreeNode;
import com.young.vo.RoleVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private MenuService menuService;

    /**
     * 查询角色集合
     * @return 角色集合
     */
    @RequestMapping("/findRoleList")
    public DataGridViewResult findRoleList(RoleVo roleVo){
        //设置分页信息
        PageHelper.startPage(roleVo.getPage(),roleVo.getLimit());
        //调用业务层方法
        List<Role> roleList = roleService.findRoleList(roleVo);
        //创建分页对象
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加角色
     * @param role 要添加的角色
     * @return 提示信息
     */
    @RequestMapping("/addRole")
    public String addRole(Role role){
        //建立map集合用于返回前台数据
        Map<String,Object> map = new HashMap<String, Object>();
        //判断结果
        if (roleService.addRole(role) > 0){
            //添加成功
            map.put(SystemConstant.SUCCESS,true);
            //提示信息
            map.put(SystemConstant.MSG,"添加成功");
        }else{
            //添加失败
            map.put(SystemConstant.SUCCESS,false);
            //提示信息
            map.put(SystemConstant.MSG,"添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 修改角色
     * @param role 要修改的角色
     * @return 修改结果影响的条数
     */
    @RequestMapping("/updateRole")
    public String updateRole(Role role){
        //建立map集合用于返回前台数据
        Map<String,Object> map = new HashMap<String, Object>();
        //判断结果
        if (roleService.updateRole(role) > 0){
            //添加成功
            map.put(SystemConstant.SUCCESS,true);
            //提示信息
            map.put(SystemConstant.MSG,"修改成功");
        }else{
            //添加失败
            map.put(SystemConstant.SUCCESS,false);
            //提示信息
            map.put(SystemConstant.MSG,"修改失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 判断是否有员工在使用当前角色
     * @param roleId 角色id
     * @return 回显数据
     */
    @RequestMapping("/checkRoleEmployee")
    public String checkRoleEmployee(Integer roleId){
        //建立集合回显数据
        Map<String,Object> map = new HashMap<String, Object>();
        //查询方法,判断结果
        if (employeeService.findEmployeeByRoleId(roleId) > 0){
            //有员工是当前角色，不能删除
            map.put(SystemConstant.EXIST,true);
            //提示不能删除信息
            map.put(SystemConstant.MSG,"当前角色存在员工，不能删除");
        }else{
            //有员工是当前角色，不能删除
            map.put(SystemConstant.EXIST,false);
        }
        return JSON.toJSONString(map);
    }

    /**
     * 删除角色
     * @param roleId 要删除的角色id
     * @return 删除结果影响的条数
     */
    @RequestMapping("/deleteRole")
    public String deleteRole(Integer roleId){
        //建立map集合用于返回前台数据
        Map<String,Object> map = new HashMap<String, Object>();
        //判断结果
        if (roleService.deleteRole(roleId) > 0){
            //添加成功
            map.put(SystemConstant.SUCCESS,true);
            //提示信息
            map.put(SystemConstant.MSG,"删除成功");
        }else{
            //添加失败
            map.put(SystemConstant.SUCCESS,false);
            //提示信息
            map.put(SystemConstant.MSG,"删除失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 初始化菜单树
     * @return 菜单树
     */
    @RequestMapping("/initMenuTree")
    public DataGridViewResult initMenuTree(Integer roleId){
        //查询所有菜单
        List<Menu> menuList = menuService.findAllMenu();
        //查询当前角色拥有的菜单集合
        List<Menu> currentList = menuService.findListByRoleId(roleId);
        //创建集合保存树节点信息
        List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
        //循环遍历所有菜单
        for (Menu menu : menuList) {
            //定义变量，标识是否选中
            String checkArr = "0";//默认值为0，表示不选中
            //遍历当前角色的菜单，将当前角色菜单选中
            for (Menu current : currentList) {
                //id值相等表示有当前菜单
                if (menu.getId().equals(current.getId())){
                    checkArr = "1";//将其选中
                    break;
                }
            }
            //定义变量，表示菜单是否展开
            Boolean spread = menu.getSpread() == null || menu.getSpread() == 1;
            treeNodeList.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),spread,checkArr));
        }
        //将保存树节点信息的集合返回
        return new DataGridViewResult(treeNodeList);
    }

    /**
     * 保存角色菜单列表
     * @return 提示信息
     */
    @RequestMapping("/saveRoleMenu")
    public String saveRoleMenu(String ids,Integer roleId){
        //创建返回数据的map集合
        Map<String,Object> map = new HashMap<String, Object>();
        //判断结果
        if (roleService.saveRoleMenu(ids, roleId) > 0){
            //添加成功
            map.put(SystemConstant.SUCCESS,true);
            //提示信息
            map.put(SystemConstant.MSG,"分配成功");
        }else{
            //添加失败
            map.put(SystemConstant.SUCCESS,false);
            //提示信息
            map.put(SystemConstant.MSG,"分配失败");
        }
        return JSON.toJSONString(map);
    }
}
