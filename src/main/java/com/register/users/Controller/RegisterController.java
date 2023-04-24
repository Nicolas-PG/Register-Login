package com.register.users.Controller;

import com.register.users.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
    @Autowired
    IUserService userService;

    @GetMapping("/login")
    public String login(){
        return"login";
    }

    @GetMapping("/")
    public String showHomepage(Model model){
        model.addAttribute("users",userService.userList());
        return "index";
    }
}
