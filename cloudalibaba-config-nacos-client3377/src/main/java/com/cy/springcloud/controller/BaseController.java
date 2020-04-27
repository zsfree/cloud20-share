package com.cy.springcloud.controller;


import com.cy.springcloud.entities.common.SOptions;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author zs
 * @Date 2020/4/11 16:12
 * @Version 1.0
 **/

public class BaseController<T> implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*"); //解决跨域访问报错
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS, HEAD, PATCH, POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600"); //设置过期时间
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // 支持HTTP 1.0. response.setHeader("Expires", "0");

        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, " +
                "WG-App-Version, WG-Device-Id, WG-Network-Type, WG-Vendor, WG-OS-Type, WG-OS-Version, WG-Device-Model, WG-CPU, WG-Sid, WG-App-Id, WG-Token, X-Token, client_id, uuid, Authorization");

        System.out.println(servletRequest.toString());
        System.out.println(servletResponse.toString());
        filterChain.doFilter(servletRequest, servletResponse);
//        System.out.println("****************");
    }

    @Override
    public void destroy() {
    }

    /**
     * 获取精确到秒的时间戳
     * @param date
     * @return
     */
    public int getSecondTimestampTwo(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime()/1000);
        return Integer.valueOf(timestamp);
    }



    protected List<SOptions> getSorts()
    {
        // 排序字段
        List<SOptions> sOptionsList = new ArrayList<>();
        SOptions sOptions = new SOptions();
        sOptions.setLabel("按ID升序");
        sOptions.setKey("+id");
        sOptionsList.add(sOptions);

        sOptions = new SOptions();
        sOptions.setLabel("按ID降序");
        sOptions.setKey("-id");
        sOptionsList.add(sOptions);
        return sOptionsList;
    }

    protected List<Map> getStatus()
    {
        // 状态
        List<Map> optionsList = new ArrayList<>();
        Map<String, Object> stringMap = new HashMap<>();
        stringMap.put("label", "删除");
        stringMap.put("prop", -1);
        optionsList.add(stringMap);

        stringMap = new HashMap<>();
        stringMap.put("label", "禁用");
        stringMap.put("prop", 0);
        optionsList.add(stringMap);

        stringMap = new HashMap<>();
        stringMap.put("label", "正常");
        stringMap.put("prop", 1);
        optionsList.add(stringMap);
        return optionsList;
    }

    protected List<String> getSearch()
    {
        // 查询字段
        List<String> fColumnList = new ArrayList<>();
        fColumnList.add("status");
        return fColumnList;
    }

    protected Map<String, Object> getTmpData ()
    {
        // 关键字段 - 暂未使用
        Map<String, Object> tData = new HashMap<String, Object>();
        tData.put("id", 0);
        tData.put("phone", "");
        tData.put("remark", "");
        tData.put("status", 0);
        tData.put("timestamp", 0);
        return tData;
    }

    protected List<Map> getYNStatus()
    {
        // 状态
        List<Map> optionsList = new ArrayList<>();
        Map<String, Object> stringMap = new HashMap<>();
        stringMap.put("label", "否");
        stringMap.put("prop", 0);
        optionsList.add(stringMap);

        stringMap = new HashMap<>();
        stringMap.put("label", "是");
        stringMap.put("prop", 1);
        optionsList.add(stringMap);
        return optionsList;
    }



    /**
     * 构建性别状态
     * @return
     */
    protected List<Map> getSexStatus()
    {
        // 状态
        List<Map> optionsList = new ArrayList<>();
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("label", "男");
        stringMap.put("prop", "1");
        optionsList.add(stringMap);

        stringMap = new HashMap<String, String>();
        stringMap.put("label", "女");
        stringMap.put("prop", "0");
        optionsList.add(stringMap);
        return optionsList;
    }
}
