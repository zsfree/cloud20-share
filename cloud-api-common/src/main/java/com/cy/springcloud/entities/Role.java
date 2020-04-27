package com.cy.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Role
 * @Description TODO
 * @Author zs
 * @Date 2020/3/28 0:56
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable
{
    private Integer id;
    private String username;
    private String password;
    private String is_superuser;
    private String last_login;
    private String first_name;
    private String last_name;
    private String email;
    private short is_staff;
    private short is_active;
    private String date_joined;
    private String phone;
    private String avatar;
    private String introduction;
    private String last_login_ip;
    private String last_login_time;
    private Integer sex;
    private String slat;
    private String create_time;
    private String update_time;
    private Integer status;

    protected String words;
    protected String timestamp;
}
