package com.example.demobackend.controller;


import com.alibaba.fastjson.JSONObject;


import com.example.demobackend.mapper.UserMapper;
import com.example.demobackend.model.User;
import com.example.demobackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController extends BaseController{


//    @RequestMapping("/login")

//    @Autowired
//    private AuthenticationManager authManager;


    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserService service;



//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(
//            HttpServletRequest reuq,
//            @RequestParam("username") String name,
//            @RequestParam("password") String password
//    ) {
//        try {
//            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(name, password);
//            Authentication authentication = authManager.authenticate(authRequest);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            HttpSession session = reuq.getSession();
//            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
//            return ok(name).toJSONString();
//        } catch (Exception e) {
//            return fail("login fail").toJSONString();
//        }
//    }

//    @RequestMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null ){
//            new SecurityContextLogoutHandler().logout(request, response, authentication);
//            return ok("ok").toJSONString();
//        }
//
//        return fail("fail").toJSONString();
//
//    }



    @RequestMapping("/getUserInfo")
    public JSONObject getUserInfo(
            Principal principal
//            @RequestParam("token") String token
    ) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String name = authentication.getName();
//        List<String> access = mapper.privileges(token);
//
        String name = principal.getName();
        User user =mapper.getByName(name);
        user.setPassword("");
        user.setAccess(mapper.privileges(name));
        if (user.getAccess() == null) {
            user.setAccess(new ArrayList<>());
        }

//        jo.put("access", access);
//        jo.put("token", token);
//        jo.put("name", token);

        return ok(user);
    }

    @RequestMapping("/list")
    public JSONObject list(
            @RequestParam("start") long start,
            @RequestParam("size") int size
    ) {
        return ok(service.list(start, size));
    }

    @RequestMapping("/count")
    public JSONObject count(
    ) {
        return ok(service.count());
    }

    @RequestMapping("/privileges")
    public JSONObject list() {

        return ok(Arrays.asList("super_admin", "admin", "normal"));
    }

    @RequestMapping("/add")
    @Transactional
    public JSONObject add(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("privilege") String privilege
    ) {
        if (mapper.getByName(name) != null) {
            return fail("用户已经存在");
        }
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        mapper.insertUser(user);
        mapper.insertPrivilege(name, privilege);
        return ok("OK");
    }
}
