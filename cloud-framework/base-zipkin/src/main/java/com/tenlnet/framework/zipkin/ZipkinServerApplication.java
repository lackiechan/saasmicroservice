package com.tenlnet.framework.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-09-03 10:18
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableZipkinServer
public class ZipkinServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }

//    @Bean
//    @Primary
//    public MySQLStorage mySQLStorage(DataSource datasource) {
//        return MySQLStorage.newBuilder().datasource(datasource).executor(Runnable::run).build();
//    }
}
