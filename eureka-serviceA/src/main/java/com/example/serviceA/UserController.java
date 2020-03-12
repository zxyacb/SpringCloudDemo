package com.example.serviceA;

import com.example.serviceA.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static Map<Long, User> users = new HashMap<>();

    static {
        users.put(1L, new User(1L,"Alpha","aaaaaa"));
        users.put(2L, new User(2L,"Beta","bbbbbb"));
        users.put(3L, new User(3L,"Gamma","cccccc"));
        users.put(4L, new User(4L,"Delta","dddddd"));
    }

    @GetMapping("/{id:\\d+}")
    public User get(@PathVariable Long id) {
        log.info("获取用户id为 " + id);
        return users.getOrDefault(id, new User());
    }
}
