package com.library.manage.intercepeter;

import com.library.manage.dao.UserMapper;
import com.library.manage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Loginintercerpetr implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        if (requestURI.equals("/login") || requestURI.equals("/register")) {
            return true;
        }

        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        if(user==null){
            response.sendRedirect("/login");
            return false;
        }
        User user1=userMapper.selectById(user.getId());
        if(user1!=null){
            return true;
        }
        else {
            response.sendRedirect("/login");
        return false;}
    }
}
