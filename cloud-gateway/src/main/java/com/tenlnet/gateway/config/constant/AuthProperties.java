package com.tenlnet.gateway.config.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-08-10 23:43
 */
@ConfigurationProperties(prefix = "auth")
@Data
public class AuthProperties {
    private String tokenKey;

}
