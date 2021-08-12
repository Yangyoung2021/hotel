package com.young.utils;

public interface SystemConstant {

    /**
     * 加密次数
     */
    Integer PASSWORD_COUNT = 5;

    /**
     * 登录用户保存的key
     */
    String LOGIN_USER = "loginUser";

    /**
     * 成功
     */
    String SUCCESS = "success";

    /**
     * 验证提示信息
     */
    String MSG = "message";

    /**
     * 是否存在员工信息
     */
    String EXIST = "exist";

    /**
     * 默认密码
     */
    String DEFAULT_PWD = "123456";
    /**
     * 文件上传目录地址
     */
    String UPLOAD_PATH = "D:/fileTransmit/ssm/hotel/upload/";

    /**
     * 网络绝对路径添加的虚拟目录
     */
    String VIRTUAL_PATH = "/hotel/show/";
    /**
     * 文件夹名称命名规则
     */
    String DIRECTORY_NAME = "yyyy-MM-dd";
    /**
     * 前台登录用户
     */
    String FRONT_LOGIN_USER = "currentUser";
}
