package com.example.serviceB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    // 服务超时，时间过长引起服务熔断
    @GetMapping("delay")
    public String testDelay(){
        try {
            log.info("waiting");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "waiting long time";
    }

    //抛出异常，引起服务熔断
    @GetMapping("err")
    public String testError() throws IOException {
        log.info("throw error");
        throw new IOException("就是个错误");
    }
}
