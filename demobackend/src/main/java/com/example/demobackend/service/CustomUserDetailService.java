package com.example.demobackend.service;

import com.example.demobackend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        var user = mapper.getByName(s);

        if (user == null) {
            throw new UsernameNotFoundException(s);
        }

        List<String> role = mapper.privileges(s);
        user.setAccess(role);
        return user;
    }
}
