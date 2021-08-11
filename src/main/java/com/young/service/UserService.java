package com.young.service;

import com.young.domain.User;



public interface UserService {

    /**
     * 用户注册功能
     * @param user 注册用户
     */
    Integer addUser(User user);

    /**
     * 校验昵称
     * @param registerName 注册昵称
     * @return 查询结果
     */
    User checkName(String registerName);

    /**
     * 登录方法
     * @param loginName 登录名
     * @param password 密码
     * @return 查询结果
     */
    User login(String loginName,String password);
}
