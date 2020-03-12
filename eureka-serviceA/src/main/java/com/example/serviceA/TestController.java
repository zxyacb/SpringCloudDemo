package com.example.serviceA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Qualifier("eurekaRegistration")
    @Autowired
    private Registration registration; // 服务注册

    @Autowired
    private DiscoveryClient client; // 服务发现客户端
    @Autowired
    RestTemplate restTemplate; // 服务发现客户端

    @GetMapping("/balance")
    public String balance() {
        String result = "Test Ribbon Balance, from instance: " + registration.getInstanceId()
                + "<br>url=" + registration.getUri()
                + "<br>service_id: " + registration.getServiceId();
        logger.info(result);
        return result;
    }

    @GetMapping("/fallback/{method}")
    public String serviceDelay(@PathVariable String method) {
        return restTemplate.getForObject("http://eureka-service-b/" + method, String.class);
    }
}
