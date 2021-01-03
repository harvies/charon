package io.github.harvies.charon.config;

import io.github.harvies.charon.config.annotation.RefreshScope;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 添加@RefreshScope后 @Value才能动态刷新
 * https://mp.weixin.qq.com/s?__biz=MzA5MTkxMDQ4MQ==&mid=2648934401&idx=1&sn=98e726ec9adda6d40663f624705ba2e4&chksm=8862103fbf15992981183abef03b4774ab1dfd990a203a183efb8d118455ee4b477dc6cba50d&token=636643900&lang=zh_CN#rd
 */
@RefreshScope
@Getter
@Component
public class Config {

    @Value("${aaa}")
    private String bbb;
}
