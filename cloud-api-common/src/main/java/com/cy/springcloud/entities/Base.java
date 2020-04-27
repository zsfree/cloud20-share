package com.cy.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @ClassName Base
 * @Description TODO
 * @Author zs
 * @Date 2020/4/21 23:59
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Base
{
    protected String words;
    protected String timestamp;

}
