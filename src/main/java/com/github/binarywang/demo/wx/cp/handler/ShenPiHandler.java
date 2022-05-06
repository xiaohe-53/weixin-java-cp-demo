package com.github.binarywang.demo.wx.cp.handler;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;

import java.util.Map;

public class ShenPiHandler extends AbstractHandler {
    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxCpXmlMessage, Map<String, Object> map, WxCpService wxCpService, WxSessionManager wxSessionManager) throws WxErrorException {
        int agentId = Integer.parseInt(wxCpXmlMessage.getAgentId());
        String eventType = wxCpXmlMessage.getEvent();
        if (!eventType.equals("sys_approval_change")) {
            System.out.println("it is NOT shenpi change event");
            return null;
        }
        WxCpXmlMessage.ApprovalInfo approvalInfo = wxCpXmlMessage.getApprovalInfo();
        System.out.println(approvalInfo);
        return WxCpXmlOutMessage.TEXT().content("OK").build();
    }
}
