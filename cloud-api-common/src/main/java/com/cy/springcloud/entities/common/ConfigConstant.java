package com.cy.springcloud.entities.common;

/**
 * @ClassName ConfigConstant
 * @Description TODO
 * @Author zs
 * @Date 2020/3/28 9:13
 * @Version 1.0
 **/
public class ConfigConstant {
    /* 根据Constants 常量 进行返回编码 */
    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MSG = "请求成功";
    public static final int EXCEPTION_CODE = 404;
    public static final String EXCEPTION_MSG = "请求处理异常，找不到数据";
    public static final int ERROR_CODE = 500;
    public static final String ERROR_MSG = "请求方式有误,请检查 GET/POST";
    public static final int NOT_URL_CODE = 501;
    public static final String NOT_URL_MSG = "请求路径不存在";
    public static final int INSUFFICIENT_AUTHORITY_CODE = 502;
    public static final String INSUFFICIENT_AUTHORITY_MSG = "权限不足";
    public static final int LOGON_EXPIRATION_CODE = 20011;
    public static final String LOGON_EXPIRATION_MSG = "登陆已过期";
}
