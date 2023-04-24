package com.register.users.Controller;

import com.register.users.Dto.UserRegisterDto;
import com.register.users.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class UserRegisterController {

    @Autowired
    private UserServiceImpl userService;

    @ModelAttribute("user")
    public UserRegisterDto returnNewUserDto(){
        return new UserRegisterDto();
    }

    @GetMapping
    public String showFormRegister(){
        return "register";
    }
    @PostMapping
    public String registerAccountUser(@ModelAttribute("user") UserRegisterDto registerDto){
        userService.saveUser(registerDto);
        return "redirect:/register?success";
    }

}
