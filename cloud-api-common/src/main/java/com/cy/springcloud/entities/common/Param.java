package com.cy.springcloud.entities.common;

import cn.hutool.json.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName Ips
 * @Description TODO
 * @Author zs
 * @Date 2020/3/28 0:56
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Param<T> implements Serializable
{
    private Integer page;
    private Integer limit;
    private String sort;
    private String cloumn;
    private T fData;
}
