package com.tencent.wxcloudrun.handler;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Astar（一颗星）
 */
@Component
public class SubscribeHandler  implements WxMpMessageHandler {

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
        throws Exception {
        //TODO
        return null;
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage  wxMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        // 获取用户OpenID
        String openid = wxMessage.getFromUser();

        // 发送欢迎消息
        String welcome = "感谢您使用智能AI助手！你可以开始和AI聊起来了";
        WxMpKefuMessage textMessage = WxMpKefuMessage.TEXT().toUser(openid)
            .content(welcome).build();

        try {
            wxMpService.getKefuService().sendKefuMessage(textMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return null;
    }
}
