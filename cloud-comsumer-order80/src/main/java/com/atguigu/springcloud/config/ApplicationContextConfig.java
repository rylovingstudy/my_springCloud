package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced  //注释掉，使用自己写的轮询算法，ribbon默认的负载规则替换
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
