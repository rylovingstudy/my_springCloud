package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //不添加的话，会报unknownhost的错误，找不到相应的服务
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    private void foo() {

    }
}
