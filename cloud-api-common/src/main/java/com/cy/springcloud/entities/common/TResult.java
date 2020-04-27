package com.cy.springcloud.entities.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName TResult
 * @Description TODO
 * @Author zs
 * @Date 2020/4/19 20:28
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TResult implements Serializable
{
    private Integer count;
    private Object results;
    private Object tableColumn;
    private Object filterColumn;
    private Object sortOptions;
    private Object tmpData;

    public static TResult format(Integer count, Object results, Object tableColumn, Object filterColumn, Object sortOptions, Object tmpData)
    {
        return new TResult(count, results, tableColumn, filterColumn, sortOptions, tmpData);
    }

}
