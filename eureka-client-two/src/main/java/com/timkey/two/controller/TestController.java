package com.timkey.two.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author tangfeng
 * @fileName TestController
 * @date 2019/6/3 17:31
 * @description xxxx
 */
@RestController
public class TestController {
    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam String name) {
        return "[eureka-client-two] hi " + name + " ,i am lucy and from port: " + port;
    }

    public String hiError(String name) {
        return "[eureka-client-two] hi," + name + ",sorry,error!";
    }
}
