package com.young.interceptor;

import com.young.utils.SystemConstant;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断session域中是否有loginUser对象
        if (request.getSession().getAttribute(SystemConstant.LOGIN_USER) == null){
            //用户没有登陆
            response.sendRedirect(request.getContextPath()+"/admin/login");
//            return true;
        }
        return true;
    }
}
