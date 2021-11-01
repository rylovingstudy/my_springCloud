package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author Ren
 */
public interface LoadBalancer {
    /**
     * 负载取出可用服务列表中的服务实例
     *
     * @param instances List<ServiceInstance> instances 可用服务列表
     * @return ServiceInstance
     */
    ServiceInstance instance(List<ServiceInstance> instances);
}
