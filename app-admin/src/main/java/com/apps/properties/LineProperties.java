package com.apps.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author: SimonYang
 * @Date: 2019-03-06
 * @Version: 1.0.0
 */
@Getter
@Setter
@Component
@Configuration
@ConfigurationProperties(prefix = "spring.boot.admin.notify.line")
@ConditionalOnProperty(prefix = "spring.boot.admin.notify.line", name = "enabled", matchIfMissing = true)
public class LineProperties {
    private boolean enabled = false;
    private String channelSecret;
    private String channelToken;
    private String to;
}
