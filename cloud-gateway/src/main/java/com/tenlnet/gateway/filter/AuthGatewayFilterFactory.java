package com.tenlnet.gateway.filter;

import com.tenlnet.gateway.config.constant.AuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-08-15 17:08
 */

@Component
@EnableConfigurationProperties(AuthProperties.class)
public class AuthGatewayFilterFactory extends AbstractGatewayFilterFactory {
    @Autowired
    private AuthProperties authProperties;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String token = request.getHeaders().getFirst(authProperties.getTokenKey());
            if (token == null) {
                token = request.getQueryParams().getFirst(authProperties.getTokenKey());
            }

            //不合法(响应未登录的异常)
            ServerHttpResponse response = exchange.getResponse();
            //设置headers
            HttpHeaders httpHeaders = response.getHeaders();
            httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
            httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            //设置body
            String warningStr = "{code}";
            DataBuffer bodyDataBuffer = response.bufferFactory().wrap(warningStr.getBytes());

            return response.writeWith(Mono.just(bodyDataBuffer));
        };
    }
}
