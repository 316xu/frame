package com.example.demobackend.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController extends BaseController{

    @RequestMapping("/test")
    public JSONObject test() {
        return ok("hhh2");
    }

}
