package com.github.binarywang.demo.wx.cp;

import com.github.binarywang.demo.wx.cp.config.single.WxCpProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * 在配置目录下，分为single和mutil目录，代表单实例和多实例方式，请自行选择配置方式
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@ComponentScans({
    @ComponentScan("com.github.binarywang.demo.wx.cp.config.single"),
    @ComponentScan("com.github.binarywang.demo.wx.cp.handler"),
    @ComponentScan("com.github.binarywang.demo.wx.cp.controller.single")})
@SpringBootApplication
public class WxCpDemoApplication {

  public static void main(String[] args) {
      System.out.println("go");
    SpringApplication.run(WxCpDemoApplication.class, args);
  }

}
