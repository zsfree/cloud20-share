package com.cy.springcloud.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cy.springcloud.entities.common.SOptions;
import com.cy.springcloud.entities.common.TColumn;
import com.cy.springcloud.entities.common.TResult;
import com.cy.springcloud.utils.TransBeamMap;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.*;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author zs
 * @Date 2020/4/11 16:12
 * @Version 1.0
 **/
public class BaseController<T> extends FilterController {

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

    /**
     * 获取栏目字段信息
     * @return
     */
    protected List<TColumn> getColumn()
    {
        // 字段
        List<TColumn> tColumnList = new ArrayList<>();
        return tColumnList;
    }


    /**
     * 获取界面元素，如列表栏目，数据，分页，过滤查询等
     * @param count
     * @param results
     * @return
     */
    protected TResult getListView(Integer count, List<T> results, String configColumns, Map<String, Object> wjData) {

        TResult jsonObject;
        try {
            jsonObject = JSONObject.parseObject(configColumns, TResult.class);
        } catch (Exception ex){
//            System.out.println("***2222*******************************::"+ex.toString());
            jsonObject = null;
        }
        if (jsonObject == null)
        {
            // 字段
            List<TColumn> tColumnList = this.getColumn();

            // 查询字段
            List<String> FColumnList = this.getSearch();

            // 排序字段
            List<SOptions> sOptionsList = this.getSorts();

            return TResult.format(count, results, tColumnList, FColumnList, sOptionsList, this.getTmpData());
        }
        System.out.println("***111*******************************::"+jsonObject);
        jsonObject.setCount(count);
        jsonObject.setResults(results);

        if (wjData != null)
        {
            // 字段
            List<TColumn> tColumnList = JSON.parseObject(jsonObject.getTableColumn().toString(), new TypeReference<List<TColumn>>(){});
            int i = 0;
            for (TColumn obj:tColumnList) {
                int len = obj.getProp().length();
                if (obj.getType() == 1 && obj.getProp().substring(len-2, len).equals("Id"))
                {
                    obj.setOptions(wjData.get(obj.getProp()));
                    tColumnList.set(i, obj);
                    jsonObject.setTableColumn(tColumnList);
                }
                i ++;
            }
        }
        return jsonObject;
    }

    /**
     * 外键数据
     * @return
     */
    protected Map<String, Object> wjData()
    {
        return null;
    }
}
