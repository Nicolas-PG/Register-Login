package com.register.users.Service;

import com.register.users.Entity.Rol;
import com.register.users.Entity.User;
import com.register.users.Repository.IUserRepository;
import com.register.users.Dto.UserRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;



    @Override
    public User saveUser(UserRegisterDto registerDto) {
        User user = new User(registerDto.getName(),registerDto.getLastname(),registerDto.getEmail(), passwordEncoder.encode(registerDto.getPassword()), Arrays.asList(new Rol("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("Invalid login");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapOutToRoles(user.getRoles()));
    }
    private Collection<?extends GrantedAuthority> mapOutToRoles(Collection<Rol> roles){
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());

    }

    @Override
    public List<User> userList() {
        return userRepository.findAll();
    }
}
