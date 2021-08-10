package com.young.dao;


import com.young.domain.Menu;
import com.young.vo.MenuVo;

import java.util.List;

public interface MenuMapper {

    /**
     * 查询所有菜单选项
     * @return 菜单集合
     */
    public List<Menu> findAllMenu();

    /**
     * 查询当前角色拥有的菜单
     * @param roleId 角色id
     * @return 菜单集合
     */
    public List<Menu> findListByRoleId(Integer roleId);

    /**
     * 查询所有菜单栏信息
     * @param menuVo 菜单传参工具类
     * @return 菜单集合
     */
    public List<Menu> findMenuListByPage(MenuVo menuVo);

    /**
     * 添加菜单
     * @param menu 要添加的菜单
     * @return 添加结果
     */
    public Integer addMenu(Menu menu);

    /**
     * 修改菜单
     * @param menu 要修改的菜单
     * @return 修改结果影响的条数
     */
    Integer updateMenu(Menu menu);

    /**
     * 根据id删除菜单
     * @param id 菜单id
     * @return 删除结果
     */
    Integer deleteById(Integer id);

    /**
     * 根据员工id查询菜单列表
     * @param id 员工id
     * @return 菜单列表
     */
    List<Menu> findListByEmpId(Integer id);
}
