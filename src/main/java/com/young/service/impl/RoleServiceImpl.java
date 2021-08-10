package com.young.service.impl;

import com.young.dao.RoleMapper;
import com.young.domain.Role;
import com.young.service.RoleService;
import com.young.vo.RoleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 查询角色集合
     * @return 角色集合
     */
    public List<Role> findRoleList(RoleVo roleVo) {
        return roleMapper.findRoleList(roleVo);
    }

    /**
     * 查询所有角色
     * @return 所有角色集合
     */
    public List<Map<String,Object>> findRoleList() {
        return roleMapper.findRoleListByMid();
    }

    /**
     * 添加角色
     * @param role 要添加的角色
     * @return 添加结果影响的条数
     */
    public Integer addRole(Role role) {
        return roleMapper.addRole(role);
    }

    /**
     * 修改角色
     * @param role 要修改的角色
     * @return 修改角色结果影响的条数
     */
    public Integer updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    /**
     * 删除角色
     * @param id 要删除角色的id
     * @return 删除角色结果影响的条数
     */
    public Integer deleteRole(Integer id) {
        return roleMapper.deleteRole(id);
    }

    /**
     * 保存角色菜单
     * @param idsStr 菜单id数组
     * @param roleId 角色id
     * @return 执行结果影响条数
     */
    public Integer saveRoleMenu(String idsStr, Integer roleId) {
        try {
            //删除之前的相对应的角色菜单
            Integer del_result = roleMapper.deleteRoleMenu(roleId);
            if (del_result > 0){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }
            //将获取的字符串转成对应格式
            String[] ids = idsStr.split(",");
            //遍历
            for (String id : ids) {
                //调用方法
                roleMapper.saveRoleMenu(id,roleId);
            }
            return 1;//成功
        } catch (Exception e) {
            return 0;//失败
        }
    }

    /**
     * 查询相应的员工对应的角色集合
     * @param eid 员工id
     * @return 角色集合
     */
    public Integer[] findRoleAndEmp(Integer eid) {
        return roleMapper.findRoleAndEmp(eid);
    }
}
