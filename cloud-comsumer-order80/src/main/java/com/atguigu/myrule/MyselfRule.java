package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定义自己的负载均衡访问算法
 */
@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule() {
        /**
         * 定义为随机
         */
        return new RandomRule();
    }
}
