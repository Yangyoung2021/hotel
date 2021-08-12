package com.young.controller;

import com.alibaba.fastjson.JSON;
import com.young.domain.User;
import com.young.service.UserService;
import com.young.utils.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@Controller
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param user 注册的用户
     * @return 回显信息
     */
    @RequestMapping("/register")
    @ResponseBody
    public String register(User user){
        //创建回显信息的集合
        Map<String,Object> map = new HashMap<String, Object>();
        if (userService.addUser(user) > 0){
            //添加成功
            map.put(SystemConstant.SUCCESS,true);
            //回显数据
            map.put(SystemConstant.MSG,"恭喜注册成功");
        }else{
            //添加成功
            map.put(SystemConstant.SUCCESS,false);
            //回显数据
            map.put(SystemConstant.MSG,"很遗憾注册失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 校验昵称是否存在
     * @param registerName 注册昵称
     * @return 回显数据
     */
    @RequestMapping("/checkName")
    @ResponseBody
    public String checkName(String registerName){
        return JSON.toJSONString(userService.checkName(registerName) != null);
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(String loginName, String password, HttpSession session){
        //创建回显信息的集合
        Map<String,Object> map = new HashMap<String, Object>();
        User loginUser = userService.login(loginName, password);
        if (loginUser != null){
            //登录成功
            map.put(SystemConstant.SUCCESS,true);
            //将信息放入session域
            session.setAttribute(SystemConstant.FRONT_LOGIN_USER,loginUser);
        }else{
            //登录失败
            map.put(SystemConstant.SUCCESS,false);
            //回显数据
            map.put(SystemConstant.MSG,"用户名或密码错误");
        }
        return JSON.toJSONString(map);
    }

}
