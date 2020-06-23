package com.tenlnet.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-08-10 23:43
 */
@ConfigurationProperties(prefix = "nginx")
public class NginxProperties {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
