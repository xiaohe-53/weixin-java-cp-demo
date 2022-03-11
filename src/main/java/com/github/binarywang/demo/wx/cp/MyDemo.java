package com.github.binarywang.demo.wx.cp;

import com.github.binarywang.demo.wx.cp.config.WxCpConfiguration;
import com.github.binarywang.demo.wx.cp.config.WxCpProperties;
import com.github.binarywang.demo.wx.cp.handler.*;
import org.springframework.beans.factory.annotation.Autowired;

public class MyDemo {
    @Autowired
    WxCpProperties properties;

    public static void main(String[] args) {
        MyDemo myDemo = new MyDemo();
        System.out.println("the corp id of my prop is:");
        System.out.println(myDemo.properties.getCorpId());
    }
}
