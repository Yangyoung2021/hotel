package com.young.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.domain.Employee;
import com.young.domain.Menu;
import com.young.service.MenuService;
import com.young.utils.*;
import com.young.vo.MenuVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/admin/menu")
public class MenuController {

    //注入MenuMapper
    @Resource
    private MenuService menuService;

    @RequestMapping("/loadMenuList")
    public String loadMenuList(HttpSession session){
        //创建map集合保存menuInfo
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        //创建map集合保存homeInfo
        Map<String, Object> homeInfo = new LinkedHashMap<String, Object>();
        //创建map集合保存logoInfo
        Map<String, Object> logoInfo = new LinkedHashMap<String, Object>();

        //获取登录用户信息
        Employee emp = (Employee) session.getAttribute(SystemConstant.LOGIN_USER);
        //查询业务层方法,获取当前员工的菜单信息
        List<Menu> menuList = menuService.findListByEmpId(emp.getId());
        //创建节点集合,保存层级关系
        List<MenuNode> menuNodeList = new ArrayList<MenuNode>();
        //将查询所得遍历创建层级关系，当前没有任何层级关系
        for (Menu menu : menuList) {
            //创建菜单节点对象接收查询集合
            MenuNode menuNode = new MenuNode();
            menuNode.setId(menu.getId());//菜单id
            menuNode.setPid(menu.getPid());//父类id
            menuNode.setHref(menu.getHref());//跳转路径
            menuNode.setIcon(menu.getIcon());//图标
            menuNode.setSpread(menu.getSpread());//是否展开
            menuNode.setTitle(menu.getTitle());//菜单名称
            menuNode.setTarget(menu.getTarget());//打开方式
            //将对象添加到集合
            menuNodeList.add(menuNode);
        }
        //保存homeInfo信息
        homeInfo.put("title","首页");
        homeInfo.put("href","/admin/role/desktop");
        //保存logoInfo信息
        logoInfo.put("title","酒店管理系统");//logo标题
        logoInfo.put("image","/statics/layui/images/logo.png");//logo图片
        logoInfo.put("href","/admin/index");//首页地址
        //保存menuInfo信息
        map.put("menuInfo", TreeUtil.toTree(menuNodeList,0));
        map.put("homeInfo",homeInfo);
        map.put("logoInfo",logoInfo);
        return JSON.toJSONString(map);
    }

    /**
     * 加载左侧菜单树
     * @return 菜单树
     */
    @RequestMapping("/loadMenuTree")
    public DataGridViewResult loadMenuTree(){
        //查询出所有菜单集合
        List<Menu> menuList = menuService.findAllMenu();
        //创建集合保存节点信息
        List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
        for (Menu menu : menuList) {
            //定义当前菜单展开状态
            boolean spread = menu.getSpread() == null || menu.getSpread() == 1;
            //将菜单信息保存到treeNodeList集合中
            treeNodeList.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),spread));
        }
        return new DataGridViewResult(treeNodeList);
    }

    /**
     * 查询所有菜单集合
     * @param menuVo 菜单传参工具类
     * @return 菜单集合
     */
    @RequestMapping("/findAllMenu")
    public DataGridViewResult findAllMenu(MenuVo menuVo){
        //将当前页码和每页展示条数赋值
        PageHelper.startPage(menuVo.getPage(),menuVo.getLimit());
        //查询所有菜单集合
        List<Menu> menuList = menuService.findMenuListByPage(menuVo);
        //将菜单集合传给分页助手
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(menuList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加菜单
     * @param menu 要添加的菜单
     * @return 回显数据
     */
    @RequestMapping("/addMenu")
    public String addMenu(Menu menu){
        //创建返回数据集合
        Map<String,Object> map = new HashMap<String, Object>();
        //判断结果
        if (menuService.addMenu(menu) > 0){
            //添加成功
            map.put(SystemConstant.SUCCESS,true);
            //返回信息
            map.put(SystemConstant.MSG,"添加成功");
        }else{
            //添加成功
            map.put(SystemConstant.SUCCESS,false);
            //返回信息
            map.put(SystemConstant.MSG,"添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 添加菜单
     * @param menu 要添加的菜单
     * @return 回显数据
     */
    @RequestMapping("/updateMenu")
    public String updateMenu(Menu menu){
        //创建返回数据集合
        Map<String,Object> map = new HashMap<String, Object>();
        //判断结果
        if (menuService.updateMenu(menu) > 0){
            //添加成功
            map.put(SystemConstant.SUCCESS,true);
            //返回信息
            map.put(SystemConstant.MSG,"更新成功");
        }else{
            //添加成功
            map.put(SystemConstant.SUCCESS,false);
            //返回信息
            map.put(SystemConstant.MSG,"更新失败");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/checkMenuHasChild")
    public String checkMenuHasChild(Integer id){
        //创建返回数据集合
        Map<String,Object> map = new HashMap<String, Object>();
        //查询出所有菜单
        List<Menu> menuList = menuService.findAllMenu();
        //先假设没有子级菜单
        map.put(SystemConstant.EXIST,false);
        //遍历
        for (Menu menu : menuList) {
            //判断是否有子级菜单
            if (menu.getPid().equals(id)){
                //当前菜单有子级菜单
                map.put(SystemConstant.EXIST,true);
                //提示信息
                map.put(SystemConstant.MSG,"当前菜单有子级菜单不可删除");
                //结束循环
                break;
            }
        }
        return JSON.toJSONString(map);
    }

    /**
     * 添加菜单
     * @param id 要添加的菜单
     * @return 回显数据
     */
    @RequestMapping("/deleteById")
    public String deleteById(Integer id){
        //创建返回数据集合
        Map<String,Object> map = new HashMap<String, Object>();
        //判断结果
        if (menuService.deleteById(id) > 0){
            //添加成功
            map.put(SystemConstant.SUCCESS,true);
            //返回信息
            map.put(SystemConstant.MSG,"删除成功");
        }else{
            //添加成功
            map.put(SystemConstant.SUCCESS,false);
            //返回信息
            map.put(SystemConstant.MSG,"删除失败");
        }
        return JSON.toJSONString(map);
    }


}
