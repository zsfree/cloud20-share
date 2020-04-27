package com.cy.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName Member
 * @Description TODO
 * @Author zs
 * @Date 2020/3/28 0:56
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Postsale implements Serializable
{
    private BigInteger id;
    private Integer customId;
    private Integer carId;
    private Integer orderId;
    private Integer memberId;
    private Integer serviceType;
    private Integer serviceProgress;
    private Short status;
    private String remark;
    private String createTime;
    private String updateTime;

    protected String words;
    protected String timestamp;
}
