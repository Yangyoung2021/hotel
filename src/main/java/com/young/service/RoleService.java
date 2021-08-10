package com.young.service;

import com.young.domain.Role;
import com.young.vo.RoleVo;

import java.util.List;
import java.util.Map;

public interface RoleService {

    /**
     * 查询角色集合
     * @return 角色集合
     */
    List<Role> findRoleList(RoleVo roleVo);

    /**
     * 查询角色集合
     * @return 角色集合
     */
    List<Map<String,Object>> findRoleList();


    /**
     * 添加角色
     * @param role 要添加的角色
     * @return 添加影响条数
     */
    Integer addRole(Role role);

    /**
     * 修改角色
     * @param role 要修改的角色
     * @return 添加影响的条数
     */
    Integer updateRole(Role role);

    /**
     * 删除角色
     * @param id 要删除角色的id
     * @return 删除结果影响的条数
     */
    Integer deleteRole(Integer id);

    /**
     * 保存角色菜单
     * @param idsStr 菜单id数组
     * @param roleId 角色id
     * @return 执行结果影响条数
     */
    Integer saveRoleMenu(String idsStr,Integer roleId);

    /**
     * 查询员工id对应的角色
     * @param eid 员工id
     * @return 角色集合
     */
    Integer[] findRoleAndEmp(Integer eid);


}
