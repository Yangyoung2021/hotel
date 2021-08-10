package com.young.service.impl;

import com.young.dao.MenuMapper;
import com.young.domain.Menu;
import com.young.service.MenuService;
import com.young.vo.MenuVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    /**
     * 查询所有菜单
     * @return 菜单集合
     */
    public List<Menu> findAllMenu() {
        return menuMapper.findAllMenu();
    }

    /**
     * 查询当前角色的菜单列表
     * @param roleId 角色id
     * @return 菜单列表
     */
    public List<Menu> findListByRoleId(Integer roleId) {
        return menuMapper.findListByRoleId(roleId);
    }

    /**
     * 查询所有的菜单集合
     * @param menuVo 菜单传参工具类
     * @return 菜单集合
     */
    public List<Menu> findMenuListByPage(MenuVo menuVo) {
        return menuMapper.findMenuListByPage(menuVo);
    }

    /**
     * 添加菜单
     * @param menu 要添加的菜单
     * @return 添加结果
     */
    public Integer addMenu(Menu menu) {
        //如果添加的是一级菜单,将0赋值给pid
        if (menu.getPid() == null){
            menu.setPid(0);
        }
        menu.setTarget("_self");
        return menuMapper.addMenu(menu);
    }

    /**
     * 修改菜单
     * @param menu 要修改的菜单
     * @return 修改结果的影响条数
     */
    public Integer updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    /**
     * 根据id删除菜单
     * @param id 菜单id
     * @return 删除结果
     */
    public Integer deleteById(Integer id) {
        return menuMapper.deleteById(id);
    }

    /**
     * 根据员工id查询菜单列表
     * @param id 员工id
     * @return 菜单列表
     */
    public List<Menu> findListByEmpId(Integer id) {
        return menuMapper.findListByEmpId(id);
    }
}
