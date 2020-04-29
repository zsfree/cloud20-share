package com.cy.springcloud;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName NacosConfigClientMain3377
 * @Description TODO
 * @Author zs
 * @Date 2020/4/3 10:06
 * @Version 1.0
 **/
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigClientMain3377
{
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClientMain3377.class, args);
    }
}
