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
public class Member implements Serializable
{
    private Integer id;
    private String username;
    private String password;
    private Short isSuperuser;
    private String firstName;
    private String lastName;
    private String email;
    private short isStaff;
    private short isActive;
    private BigInteger dateJoined;
    private String phone;
    private String avatar;
    private String introduction;
    private String lastLoginIp;
    private String lastLoginTime;
    private Short sex;
    private String slat;
    private Short status;
    private String createTime;
    private String updateTime;

    protected String words;
    protected String timestamp;
}
