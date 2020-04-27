package com.cy.springcloud.entities.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CommonResult
 * @Description TODO
 * @Author zs
 * @Date 2020/3/28 0:58
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>
{
    private Integer code;
    private String message;
    private T      data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

    public static CommonResult fail(Object data)
    {
        return new CommonResult(400, "请求失败", data);
    }

    public static CommonResult ok(Object data)
    {
        return new CommonResult(200, "请求成功", data);
    }
}
