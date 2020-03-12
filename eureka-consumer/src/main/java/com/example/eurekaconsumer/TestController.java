package com.example.eurekaconsumer;

import com.example.eurekaconsumer.model.User;
import com.example.eurekaconsumer.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String testRibbon() {
        return restTemplate.getForObject("http://eureka-service-a/balance", String.class);//.getBody();
    }

    @GetMapping("user/{id}")
    public User testFallback(@PathVariable long id) {
        return userService.findUser(id);
    }

    @GetMapping("user/random")
    public User testUser() {
        return userService.findUser(0);
    }

    // 测试：服务熔断，超时或出现异常，调用fallback方法。调用A服务，A服务再调用B服务
    // Hystrix的默认超时时间参数是1秒（从chrome的network时间流中可观察到）
    @HystrixCommand(fallbackMethod = "callServiceABFallback")
    @GetMapping("delay")
    public String callServiceAB1() {
        return restTemplate.getForObject("http://eureka-service-a/fallback/delay", String.class);
    }

    @HystrixCommand(fallbackMethod = "callServiceABFallback")
    @GetMapping("err")
    public String callServiceAB2() {
        return restTemplate.getForObject("http://eureka-service-a/fallback/err", String.class);
    }

    public String callServiceABFallback() {
        return "R.I.P, service B";
    }

}
