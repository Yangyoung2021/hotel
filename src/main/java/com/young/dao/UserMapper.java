package com.young.dao;

import com.young.domain.User;

public interface UserMapper {

    /**
     * 用户注册功能
     * @param user 注册用户
     */
    Integer addUser(User user);

    /**
     * 根据昵称查询用户
     * @param registerName 注册昵称
     * @return 查询结果
     */
    User checkName(String registerName);
}
