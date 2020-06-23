package com.tenlnet.gateway.config.constant;

import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-08-11 09:02
 */
//@ConfigurationProperties("spring.cloud.gateway")
//@Validated
public class GatewayExtProperties {
    @NotNull
    @Valid
    private List<RouteDefinition> routes = new ArrayList();
    private List<FilterDefinition> defaultFilters = new ArrayList();
    private List<MediaType> streamingMediaTypes;

    public List<RouteDefinition> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteDefinition> routes) {
        this.routes = routes;
    }

    public List<FilterDefinition> getDefaultFilters() {
        return defaultFilters;
    }

    public void setDefaultFilters(List<FilterDefinition> defaultFilters) {
        this.defaultFilters = defaultFilters;
    }

    public List<MediaType> getStreamingMediaTypes() {
        return streamingMediaTypes;
    }

    public void setStreamingMediaTypes(List<MediaType> streamingMediaTypes) {
        this.streamingMediaTypes = streamingMediaTypes;
    }
}
