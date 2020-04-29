package com.cy.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @ClassName Order
 * @Description TODO
 * @Author zs
 * @Date 2020/3/28 0:56
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable
{
    private BigInteger id;
    private BigInteger orderNo;
    private BigInteger customId;
    private BigInteger carId;
    private BigDecimal price;
    private String useDuration;
    private BigDecimal total;
    private String useCarTime;
    private String returnCarTime;
    private BigDecimal customPay;
    private String payTime;
    private Integer payType;
    private BigInteger cityId;
    private String destination;
    private String occurred;
    private String remark;
    private Short status;
    private Integer memberId;
    private String createTime;
    private String updateTime;

    protected String words;
    protected String timestamp;
}
