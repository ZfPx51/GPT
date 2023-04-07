package com.tencent.wxcloudrun.handler;



import com.tencent.wxcloudrun.utils.JsonUtils;
import com.tencent.wxcloudrun.utils.OpenAIAPI;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
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
public class LogHandler extends AbstractHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        this.logger.info("\n接收到请求消息，内容：{}", JsonUtils.toJson(wxMessage));
        // 获取用户OpenID
        String openid = wxMessage.getFromUser();

        String msg= OpenAIAPI.chat(wxMessage.getContent());
        WxMpKefuMessage textMessage = WxMpKefuMessage.TEXT().toUser(openid)
            .content(msg).build();

        try {
            wxMpService.getKefuService().sendKefuMessage(textMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return null;

    }

}
