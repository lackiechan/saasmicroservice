package com.tenlnet.gateway.controller;

import com.tenlnet.gateway.provider.GatewaySwaggerResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger.web.*;

import java.util.List;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-08-02 16:15
 */
@RestController
@RequestMapping("/swagger-resources")
public class SwaggerResourceController {
    private GatewaySwaggerResourceProvider swaggerResourceProvider;

    @Autowired
    public SwaggerResourceController(GatewaySwaggerResourceProvider swaggerResourceProvider) {
        this.swaggerResourceProvider = swaggerResourceProvider;
    }

    @RequestMapping(value = "/configuration/security")
    public ResponseEntity<SecurityConfiguration> securityConfiguration() {
        return new ResponseEntity<>(SecurityConfigurationBuilder.builder().build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/configuration/ui")
    public ResponseEntity<UiConfiguration> uiConfiguration() {
        return new ResponseEntity<>(UiConfigurationBuilder.builder().build(), HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<List<SwaggerResource>> swaggerResources() {
        return new ResponseEntity<>(swaggerResourceProvider.get(), HttpStatus.OK);
    }





//    @Autowired(required = false)
//    private SecurityConfiguration securityConfiguration;
//    @Autowired(required = false)
//    private UiConfiguration uiConfiguration;
//    private final SwaggerResourcesProvider swaggerResources;
//
//    @Autowired
//    public SwaggerResourceController(SwaggerResourcesProvider swaggerResources) {
//        this.swaggerResources = swaggerResources;
//    }
//
//
//    @GetMapping("/configuration/security")
//    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration() {
//        return Mono.just(new ResponseEntity<>(
//                Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()), HttpStatus.OK));
//    }
//
//    @GetMapping("/configuration/ui")
//    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration() {
//        return Mono.just(new ResponseEntity<>(
//                Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()), HttpStatus.OK));
//    }
//
//    @GetMapping("")
//    public Mono<ResponseEntity> swaggerResources() {
//        return Mono.just((new ResponseEntity<>(swaggerResources.get(), HttpStatus.OK)));
//    }

}
