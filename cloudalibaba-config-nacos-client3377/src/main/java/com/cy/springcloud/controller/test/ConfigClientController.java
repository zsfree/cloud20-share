package com.cy.springcloud.controller.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName ConfigClientController
 * @Description TODO
 * @Author zs
 * @Date 2020/4/3 10:22
 * @Version 1.0
 **/
@RestController
@RefreshScope   // 该注解保证nacos能够执行动态刷新
public class ConfigClientController
{
    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
