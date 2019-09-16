package com.hao.usercenter.config;

import com.hao.commonmodel.user.WechatInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "wechat")
public class WechatConfig {

    private String domain;
    private Map<String, WechatInfo> infos;

}
