package com.library.manage.control;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.library.manage.dao.UserMapper;
import com.library.manage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class Register {

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/register")
    public String register(Model model){
        model.addAttribute("error",null);
        model.addAttribute("msg",null);
        return "register";
    }

    @PostMapping(value = "/register")
    public String processRegister(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String repassword,

            Model model
    ){

        if(password.equals(repassword)==false){

            model.addAttribute("error","两次输入密码不一致，请重新输入");
            return "register";
        }

        User user=userMapper.selectOne(Wrappers.<User>query().eq("username",username));
        if(user!=null){
            model.addAttribute("error","用户名已存在");
            return "register";
        }

        User user1=userMapper.selectOne(Wrappers.<User>query().eq("email",email));
        if(user1!=null){
            model.addAttribute("error","邮箱已存在");
            return "register";
        }

        User user2=new User();
        user2.setUsername(username);
        user2.setEmail(email);
        user2.setPassword(password);
        user2.setCreateTime(new Date());
        userMapper.insert(user2);
        model.addAttribute("error",null);
        model.addAttribute("msg","注册成功去登录");

        return "register";

    }
}
