package com.young.service.impl;

import com.young.dao.UserMapper;
import com.young.domain.User;
import com.young.service.UserService;
import com.young.utils.PasswordUtil;
import com.young.utils.SystemConstant;
import com.young.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 添加用户
     * @param user 注册用户
     * @return 添加结果
     */
    public Integer addUser(User user) {
        //设置创建时间按
        user.setCreateDate(new Date());
        //自动生成盐值
        user.setSalt(UUIDUtils.UUIDRandom());//shiro安全验证
        //密码加密
        user.setPassword(PasswordUtil.md5(user.getPassword(),user.getSalt(),SystemConstant.PASSWORD_COUNT));
        return userMapper.addUser(user);
    }

    /**
     * 校验昵称
     * @param registerName 注册昵称
     * @return 查询结果
     */
    public User checkName(String registerName) {
        return userMapper.checkName(registerName);
    }

    /**
     * 登录方法
     * @param loginName 登录名
     * @param password 密码
     * @return 查询结果
     */
    public User login(String loginName, String password) {
        //获取查询结果
        User loginUser = userMapper.checkName(loginName);
        if (loginUser != null){
            //密码加盐
            String newPassword = PasswordUtil.md5(password, loginUser.getSalt(), SystemConstant.PASSWORD_COUNT);
            //校验密码
            if (loginUser.getPassword().equals(newPassword)){
                return loginUser;
            }
        }
        return null;
    }
}
