package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("consumer")
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";

    // 通过在eureka上注册过的微服务名称调用
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    public static final String PAYMENT_SERVICE = "CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("payment/selectOne/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/selectOne/" + id, CommonResult.class);
    }

    @GetMapping("payment/selectOne/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/selectOne/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }
        return new CommonResult<>(444, "fault");
    }

    @GetMapping("payment/lb")
    public Object getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances(PAYMENT_SERVICE);
        if (instances == null || instances.size() == 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instance(instances);
        URI uri = serviceInstance.getUri();
        System.out.println(
                "uri: " + serviceInstance.getUri() +
                        " host: " + serviceInstance.getHost() +
                        " meta: " + serviceInstance.getMetadata() +
                        " id: " + serviceInstance.getServiceId() +
                        " scheme: " + serviceInstance.getScheme() +
                        " port: " + serviceInstance.getPort()
        );

        String s = uri + "/payment/lb" + " ------ " + restTemplate.getForObject(uri + "/payment/lb", String.class);
        HashMap result = new HashMap<>();
        result.put(s, s);
        return (result);
//        return uri+"/payment/lb";
    }
}
