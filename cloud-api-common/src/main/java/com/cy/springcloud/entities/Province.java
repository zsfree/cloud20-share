package com.cy.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName Province
 * @Description TODO
 * @Author zs
 * @Date 2020/3/28 0:56
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Province implements Serializable
{
    private BigInteger id;
    private BigInteger areaId;
    private String name;
    private String state;
    private Short status;
    private String remark;
    private String createTime;
    private String updateTime;

    protected String words;
    protected String timestamp;
}
