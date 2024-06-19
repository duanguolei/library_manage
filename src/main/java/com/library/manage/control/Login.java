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

import javax.servlet.http.HttpSession;

@Controller
public class Login {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("error",null);
        model.addAttribute("msg",null);
        return "login";

    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password, HttpSession httpSession, Model model){
        User user=userMapper.selectOne(Wrappers.<User>query().eq("username",username));

        if(user!=null){
            if(user.getPassword().equals(password)){
                httpSession.setAttribute("user",user);
                httpSession.setMaxInactiveInterval(60*60*24);
                return "redirect:/";
            }
            else {
                model.addAttribute("error", "密码错误");
                return "login";
            }
        }
        else {
            model.addAttribute("error","用户不存在");
            return "login";
        }
    }

}
