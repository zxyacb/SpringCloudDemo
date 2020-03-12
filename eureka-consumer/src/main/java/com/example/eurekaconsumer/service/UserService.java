package com.example.eurekaconsumer.service;

import com.example.eurekaconsumer.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.bouncycastle.asn1.cms.OtherRecipientInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service("userService")
public class UserService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "findUserFallback")
    public User findUser(long id) {
        if(id==0){
            //随机1~6：1~4返回正常，其它抛出除0异常
            int r = new Random().nextInt(6) + 1;
            if (r > 4) r=r/0;
            else id = r;
        }
        return restTemplate.getForObject("http://eureka-service-A/user/" + id, User.class);
    }

    public User findUserFallback(long id, Throwable e) {
        log.info("enter fallback in id=" + id);
        log.info(e.toString());
        return new User(-1L, "nobody", "none");
    }
}
