package com.young.controller.admin;


import com.young.utils.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 负责跳转后台页面
 */
@Controller
@RequestMapping("/admin")
public class SystemController {

    /**
     * 去到登录页面
     * @return 登陆界面
     */
    @RequestMapping("/login")
    public String login(){
        return "admin/login";
    }

    /**
     * 去到后台主页面
     * @return 后台主页
     */
    @RequestMapping("/index")
    public String index(){
        return "admin/index";
    }

    /**
     * 退出登录去到登录页面
     * @param session session域对象
     * @return 登录页面
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //退出登录，将session中的对象移除
        session.removeAttribute(SystemConstant.LOGIN_USER);
        //另一种方法
//        session.invalidate();
        return "redirect:/admin/login";
    }

    /**
     * 去到部门管理页面
     * @return 部门管理页面
     */
    @RequestMapping("/deptManager")
    public String deptManager(){
        return "admin/dept/deptManager";
    }

    /**
     * 去到部门管理页面
     * @return 部门管理页面
     */
    @RequestMapping("/roleManager")
    public String roleManager(){
        return "admin/role/roleManager";
    }

    /**
     * 去到员工页面
     * @return 员工页面
     */
    @RequestMapping("/toEmployeeManager")
    public String toEmployee(){
        return "admin/employee/employeeManager";
    }

    /**
     * 去到菜单管理页面
     * @return 菜单管理页面
     */
    @RequestMapping("/toMenuManager")
    public String toMenuManager(){
        return "admin/menu/menuManager";
    }

    /**
     * 去到楼层管理页面
     * @return 楼层管理页面
     */
    @RequestMapping("/toFloorManager")
    public String toFloorManager(){
        return "admin/floor/floorManager";
    }

    /**
     * 去到房间类型管理页面
     * @return 房间类型管理页面
     */
    @RequestMapping("/toRoomTypeManager")
    public String toRoomTypeManager(){
        return "admin/roomType/roomTypeManager";
    }

    /**
     * 去到房间管理页面
     * @return 房间管理页面
     */
    @RequestMapping("/toRoomManager")
    public String toRoomManager(){
        return "admin/room/roomManager";
    }

    /**
     * 去到订单管理页面
     * @return 订单管理页面
     */
    @RequestMapping("/toOrdersManager")
    public String toOrdersManager(){
        return "admin/orders/ordersManager";
    }
}

