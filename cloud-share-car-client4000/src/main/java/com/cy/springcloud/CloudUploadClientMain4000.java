package com.cy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName NacosConfigClientMain3377
 * @Description TODO
 * @Author zs
 * @Date 2020/4/3 10:06
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class CloudUploadClientMain4000
{
    public static void main(String[] args) {
        SpringApplication.run(CloudUploadClientMain4000.class, args);
    }
}
