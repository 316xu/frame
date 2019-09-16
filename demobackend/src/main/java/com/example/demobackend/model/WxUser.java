package com.example.demobackend.model;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class WxUser {

    private String uid;
    private String nick;
    private Timestamp signUpTime;
    private Timestamp lastLoginTime;
}
