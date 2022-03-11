package com.github.binarywang.demo.wx.cp;

import com.github.binarywang.demo.wx.cp.config.WxCpConfiguration;
import com.github.binarywang.demo.wx.cp.config.WxCpProperties;
import com.github.binarywang.demo.wx.cp.handler.*;

public class MyDemo {
    public static void main(String[] args) {
        WxCpConfiguration config = new WxCpConfiguration(new LogHandler(),
            new NullHandler(), new LocationHandler(),
            new MenuHandler(), new MsgHandler(),
            new UnsubscribeHandler(), new SubscribeHandler(), new WxCpProperties());

//        System.out.println(config);
        WxCpProperties properties = new WxCpProperties();
        System.out.println(properties.getCorpId());
        System.out.println(properties.getAppConfigs());
    }
}
