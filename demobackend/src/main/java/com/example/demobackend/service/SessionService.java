package com.example.demobackend.service;

public interface SessionService {

    String newSession3rd(String uid, String session);

    boolean check(String session3rd);

    String getUid(String session3rd);

    String getSession(String session3rd);


}
