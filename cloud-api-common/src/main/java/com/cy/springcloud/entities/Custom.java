package com.cy.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName Custom
 * @Description TODO
 * @Author zs
 * @Date 2020/3/28 0:56
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Custom implements Serializable
{
    private BigInteger id;
    private String phone;
    private String name;
    private String slat;
    private Integer sex;
    private String birth;
    private Integer status;
    private Short state;
    private String createTime;
    private String updateTime;
    private String remark;

    protected String words;
    protected String timestamp;
}
