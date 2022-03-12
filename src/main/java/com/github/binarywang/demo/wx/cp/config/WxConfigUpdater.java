package com.github.binarywang.demo.wx.cp.config;

import com.github.binarywang.demo.wx.cp.handler.*;
import lombok.val;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.constant.WxCpConsts;
import me.chanjar.weixin.cp.message.WxCpMessageHandler;
import me.chanjar.weixin.cp.message.WxCpMessageRouter;

public class WxConfigUpdater {
    public static boolean registerHandlers4Agent(int agentId,
                                                 WxCpMessageHandler msgHandler,
                                                 WxCpMessageHandler logHandler,
                                                 WxCpMessageHandler menuHandler,
                                                 WxCpMessageHandler nullHandler,
                                                 WxCpMessageHandler subscribeHandler,
                                                 WxCpMessageHandler unsubscribeHandler,
                                                 WxCpMessageHandler locationHandler,
                                                 WxCpMessageHandler contactChangeHandler,
                                                 WxCpMessageHandler enterAgentHandler) {
        if (logHandler == null) {
            logHandler = new LogHandler();
        }
        if (menuHandler == null) {
            menuHandler = new MenuHandler();
        }
        if (nullHandler == null) {
            nullHandler = new NullHandler();
        }
        if (subscribeHandler == null) {
            subscribeHandler = new SubscribeHandler();
        }
        if (unsubscribeHandler == null) {
            unsubscribeHandler = new UnsubscribeHandler();
        }
        if (locationHandler == null) {
            locationHandler = new LocationHandler();
        }
        if (contactChangeHandler == null) {
            contactChangeHandler = new ContactChangeHandler();
        }
        if (enterAgentHandler == null) {
            enterAgentHandler = new EnterAgentHandler();
        }

        WxCpService service = WxCpConfiguration.getCpService(agentId);

        final val newRouter = new WxCpMessageRouter(service);

        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(logHandler).next();

        // 自定义菜单事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.MenuButtonType.CLICK).handler(menuHandler).end();

        // 点击菜单链接事件（这里使用了一个空的处理器，可以根据自己需要进行扩展）
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.MenuButtonType.VIEW).handler(nullHandler).end();

        // 关注事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.SUBSCRIBE).handler(subscribeHandler)
            .end();

        // 取消关注事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.UNSUBSCRIBE)
            .handler(unsubscribeHandler).end();

        // 上报地理位置事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.LOCATION).handler(locationHandler)
            .end();

        // 接收地理位置消息
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.LOCATION)
            .handler(locationHandler).end();

        // 扫码事件（这里使用了一个空的处理器，可以根据自己需要进行扩展）
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.SCAN).handler(nullHandler).end();

        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxCpConsts.EventType.CHANGE_CONTACT).handler(contactChangeHandler).end();

        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxCpConsts.EventType.ENTER_AGENT).handler(enterAgentHandler).end();

        // 默认
        newRouter.rule().async(false).handler(msgHandler).end();

        WxCpConfiguration.getRouters().put(agentId, newRouter);

        return true;
    }

    public static boolean registerMsgHandlers4Agent(int agentId,
                                                    WxCpMessageHandler msgHandler) {
        return registerHandlers4Agent(agentId, msgHandler, null,
            null, null, null, null,
            null, null, null);
    }

}
