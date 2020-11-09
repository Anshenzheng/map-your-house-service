package com.an.maphouse.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Annan An
 * @date 2020/5/22 13:18
 */
@Configuration
@ConfigurationProperties(prefix = "aliyun.sms")
@EnableConfigurationProperties
@Data
public class ALiYunSmsProperty {

    private String accessKey;

    private String accessSecret;

    private String signName;

    private String templateCode;

    private Integer length;

    private Integer expireIn;

    private Integer resendInterval;
}
