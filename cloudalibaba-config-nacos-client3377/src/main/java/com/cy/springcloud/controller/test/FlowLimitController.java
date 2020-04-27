package com.cy.springcloud.controller.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import static org.apache.tomcat.jni.Time.sleep;


/**
 * @ClassName FlowLimitController
 * @Description TODO
 * @Author zs
 * @Date 2020/4/7 21:55
 * @Version 1.0
 **/
@RestController
@Slf4j
public class FlowLimitController
{

    @GetMapping("/testA")
    public String testA()
    {
        return "*********testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        log.info(Thread.currentThread().getName() + ":\t ....... ******testB");
        return "*********testB";
    }

    @GetMapping("/testD")
    public String testD()
    {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("测试RT");
        return "*********testD";
    }
}
