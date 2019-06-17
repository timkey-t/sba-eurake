package com.tinkey.one.timer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author tangfeng
 * @fileName TestTask
 * @date 2019/6/4 15:45
 * @description xxxx
 */
@Component
@Slf4j
public class TestTask {
    /**
     * 每隔一秒执行一次任务test01
     */
    //@Scheduled(fixedDelay = 1000)
    @Scheduled(cron = "0 0 12 * * ?" )
    public void test01() {
        log.info("my-test01 start");
        log.info("执行业务逻辑...");
        log.info("my-test01 end");
    }
    /**
     * 每隔一秒执行一次任务test02
     */
    //@Scheduled(fixedDelay = 1000)
    @Scheduled(cron = "0 0 12 * * ?" )
    public void test02() {
        log.info("my-test02 start");
        try {
            //睡1分钟
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("my-test02 end");
    }
}
