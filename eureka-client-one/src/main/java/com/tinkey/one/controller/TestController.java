package com.tinkey.one.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangfeng
 * @fileName TestController
 * @date 2019/6/3 17:21
 * @description 测试controller
 */
@RestController
@RequestMapping("/test")
@CacheConfig(cacheNames = "TestCache")
@Slf4j
public class TestController {
    @Value("${server.port}")
    String port;

    @RequestMapping("/index")
    //@HystrixCommand(fallbackMethod = "hiError")
    @Cacheable
    public String home(@RequestParam String name) {
        log.info("进入了！");
        return "[eureka-client-one] hi " + name + " ,i am lucy and from port: " + port;
    }

    /*public String hiError(String name) {
        return "[eureka-client-one] hi," + name + ",sorry,error!";
    }*/
}
