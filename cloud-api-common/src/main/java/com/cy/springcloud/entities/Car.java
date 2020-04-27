package com.cy.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName Car
 * @Description TODO
 * @Author zs
 * @Date 2020/3/28 0:56
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable
{
    private BigInteger id;
    private String carSerialNum;
    private String carBatch;
    private String carType;
    private Integer areaId;
    private Integer provinceId;
    private Integer cityId;
    private Integer isUse;
    private Integer isHitch;
    private Integer hitchId;
    private String useTime;
    private String location;
    private Integer memberId;
    private Short status;
    private String remark;
    private String createTime;
    private String updateTime;

    protected String words;
    protected String timestamp;
}
