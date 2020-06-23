package com.tenlnet.framework.monitor.config;

import com.tenlnet.framework.monitor.notify.WechatNotifier;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-09-01 17:43
 */
@Configuration
public class WechatNotifyConfig {
    @Bean
    @ConditionalOnMissingBean
    public WechatNotifier wechatNotifier(InstanceRepository repository) {
        return new WechatNotifier(repository);
    }
}
