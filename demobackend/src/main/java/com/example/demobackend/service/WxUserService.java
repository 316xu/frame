package com.example.demobackend.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.example.demobackend.config.WxMaConfiguration;
import com.example.demobackend.mapper.WxUserMapper;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WxUserService {
    private final Logger LOG = LogManager.getLogger();
    @Value("${wx.app_id}")
    private String APP_ID;
    @Value("${wx.secret}")
    private String SECRET;

    @Autowired
    private RestTemplate RT;

    @Autowired
    private WxUserMapper userMapper;

    @Autowired
    private SessionService sessionSvc;


    /**
     * 返回 3rd session
     *
     * @param code
     * @return
     */
    public String login(String code) {


        final WxMaService wxMaService = WxMaConfiguration.getMaService(APP_ID);
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);

            var uid = session.getOpenid();
            var sessionKey = session.getSessionKey();


            if (userMapper.get(uid) == null) {
                // new user
                userMapper.add(uid);
            } else {
                userMapper.login(uid);
            }
            return sessionSvc.newSession3rd(uid, sessionKey);

        } catch (WxErrorException e) {
            LOG.warn(e);
            return null;
        }


    }


}
