package com.example.demobackend.service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SessionServiceMemImpl implements SessionService {
    private Map<String, String> uidMap = new HashMap<>();
    private Map<String, String> sessionMap = new HashMap<>();

    public SessionServiceMemImpl() {

        uidMap.put("12345", "12345");
    }

    @Override
    public String newSession3rd(String uid, String session) {
        String key = UUID.randomUUID().toString();
        uidMap.put(key, uid);
        sessionMap.put(key, session);
        return key;
    }

    @Override
    public boolean check(String session3rd) {
        return uidMap.containsKey(session3rd);
    }

    @Override
    public String getUid(String session3rd) {
        return uidMap.get(session3rd);
    }

    @Override
    public String getSession(String session3rd) {
        return sessionMap.get(session3rd);
    }
}
