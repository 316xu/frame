package com.example.demobackend.service;


import com.example.demobackend.mapper.UserMapper;
import com.example.demobackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;


    public List<User> list(long start, int size) {

        List<User> user = mapper.list(start, size);

        for (User user1 : user) {
            user1.setAccess(mapper.privileges(user1.getName()));
            user1.setPassword("");
        }
        return user;
    }

    public long count() {
        return mapper.count();
    }
}
