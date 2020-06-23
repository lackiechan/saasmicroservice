package com.tenlnet.gateway.filter;

import com.tenlnet.gateway.config.NginxProperties;
import com.tenlnet.gateway.provider.GatewaySwaggerResourceProvider;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-08-10 10:40
 */
@Component
@EnableConfigurationProperties(NginxProperties.class)
public class SwaggerHeaderFilter extends AbstractGatewayFilterFactory {
    private static final String HEADER_NAME = "X-Forwarded-Prefix";
    @Autowired
    private NginxProperties nginxProperties;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path =nginxProperties.getPath()+request.getURI().getPath();
            if (!StringUtils.endsWithIgnoreCase(path, GatewaySwaggerResourceProvider.SWAGGER2_URL)) {
                return chain.filter(exchange);
            }

            String basePath = path.substring(0, path.lastIndexOf(GatewaySwaggerResourceProvider.SWAGGER2_URL));


            ServerHttpRequest newRequest = request.mutate().header(HEADER_NAME, basePath).build();
            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
            return chain.filter(newExchange);
        };
    }


}
