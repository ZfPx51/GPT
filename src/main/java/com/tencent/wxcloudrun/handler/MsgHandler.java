package com.tencent.wxcloudrun.handler;

import com.tencent.wxcloudrun.utils.JsonUtils;
import com.tencent.wxcloudrun.utils.OpenAIAPI;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * Astar（一颗星）
 */
@Component
public class MsgHandler extends AbstractHandler {
    public  static  final Map<String, Integer> dataMap = new HashMap<>();

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        // 获取用户OpenID
        String openid = wxMessage.getFromUser();

        String msg=OpenAIAPI.chat(wxMessage.getContent());
        WxMpKefuMessage textMessage = WxMpKefuMessage.TEXT().toUser(openid)
            .content(msg).build();

        try {
            weixinService.getKefuService().sendKefuMessage(textMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return null;

    }
}
