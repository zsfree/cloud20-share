package com.cy.springcloud.controller.test;

import com.alibaba.fastjson.JSONObject;
import com.cy.springcloud.entities.common.TResult;
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
    @Value("${config.columns.area}")
    private String configColumns;

    @GetMapping(value = "/config/info")
    public String getConfigInfo() {
        return configInfo;
    }

    @GetMapping(value = "/config/columns")
    public String getConfigColumns() {
        System.out.println("************");
        System.out.println(configColumns);
        TResult jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(configColumns, TResult.class);
        } catch (Exception ex){
            System.out.println("**********************************::"+ex.toString());
        }

        System.out.println(jsonObject.getFilterColumn());
        return "---------";
    }
}
