package com.tenlnet.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-07-29 10:13
 */
@SpringBootApplication
@EnableEurekaClient
//@ComponentScan(basePackages={"com.kdhit.dev.projecttask"})
public class GateWayApplication {
    @RequestMapping("/")
    public String home() {
        return "forward:/swagger-ui.html";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(GateWayApplication.class, args);
    }


//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                //basic proxy
//                .route(r -> r.path("/project/**")
//                        .uri("lb://project")
//                ).build();
//    }

}
