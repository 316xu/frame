package com.example.demobackend.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demobackend.constant.HttpCode;
import com.example.demobackend.constant.HttpKey;

import java.io.Serializable;
import java.util.Collection;

public abstract  class BaseController {

    public JSONObject ok(Serializable data) {
        JSONObject jo = new JSONObject();
        jo.put(HttpKey.CODE, HttpCode.OK);
        jo.put(HttpKey.DATA, data);
        return jo;
    }

    public JSONObject ok(Collection<? extends Serializable> data) {
        JSONObject jo = new JSONObject();
        jo.put(HttpKey.CODE, HttpCode.OK);
        jo.put(HttpKey.DATA, data);
        return jo;
    }

    public JSONObject fail(String msg) {
        JSONObject jo = new JSONObject();
        jo.put(HttpKey.CODE, HttpCode.FAIL);
        jo.put(HttpKey.MSG, msg);
        return jo;

    }
}
