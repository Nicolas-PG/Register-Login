package com.register.users.Service;

import com.register.users.Entity.User;
import com.register.users.Dto.UserRegisterDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    User saveUser(UserRegisterDto registerDto);

    List<User> userList();
}
