package com.example.demobackend.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demobackend.constant.HttpCode;
import com.example.demobackend.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/wechat")
public class WechatController extends BaseController{

    @Autowired
    private WxUserService mWxUserService;




    @RequestMapping("/login")
    public JSONObject login(
            @RequestParam("code") String code
    ) {
        Map<String, String> data = new HashMap<>();

        String session3rd = mWxUserService.login(code);


        if (StringUtils.isEmpty(session3rd)) {
            return fail(HttpCode.WX_LOGIN_FAIL, "登录失败");
        }

        data.put("session3rd", session3rd);

        return ok(data);
    }

}
