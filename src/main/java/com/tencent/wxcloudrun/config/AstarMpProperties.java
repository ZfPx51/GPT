package com.tencent.wxcloudrun.config;


import com.tencent.wxcloudrun.utils.JsonUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * wechat mp properties
 *
 * Astar（一颗星）
 */
@Data
public class AstarMpProperties {
    /**
     * 是否启用模板推送
     */
    private boolean templateEnable;

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
