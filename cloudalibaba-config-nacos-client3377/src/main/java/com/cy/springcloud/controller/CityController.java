package com.cy.springcloud.controller;

import com.cy.springcloud.entities.City;
import com.cy.springcloud.entities.common.*;
import com.cy.springcloud.service.CityService;
import com.cy.springcloud.service.CarService;
import com.cy.springcloud.service.MemberService;
import com.cy.springcloud.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CityController
 * @Description TODO
 * @Author zs
 * @Date 2020/4/10 23:59
 * @Version 1.0
 **/
@RestController
@Slf4j
@RefreshScope
public class CityController extends BaseController<City>
{
    @Resource
    CityService cityService;
    @Resource
    ProvinceService provinceService;

    @Value("${config.columns.city}")
    protected String configColumns;

    /**
     * 删除数据
     * @param record
     * @return
     */
    @PostMapping(value = "/city/fetchDelete")
    public CommonResult fetchDelete(@RequestBody(required=false) City record)
    {
        Integer result = 0;
        try
        {
            result = cityService.deleteByPrimaryKey(record.getId().intValue());
            return CommonResult.ok(result);
        }
        catch (Exception ex)
        {
            return CommonResult.ok(ex);
        }
    }

    /**
     * 新增数据
     * @param record
     * @return
     */
    @PostMapping(value = "/city/fetchAdd")
    public CommonResult fetchAdd(@RequestBody(required=false) City record)
    {
        Integer result = 0;
        try
        {
            result = cityService.insert(record);
            return CommonResult.ok(result);
        }
        catch (Exception ex)
        {
            return CommonResult.fail(ex);
        }
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    @PostMapping(value = "/city/fetchEdit")
    public CommonResult fetchEdit(@RequestBody(required=false) City record)
    {
        Integer result = 0;
        try
        {
            result = cityService.updateByPrimaryKey(record);
            return CommonResult.ok(result);
        }
        catch (Exception ex)
        {
            return CommonResult.ok(ex);
        }
    }

    /**
     * 更新操作状态
     * @param record
     * @return
     */
    @PostMapping(value = "/city/fetchStatus")
    public CommonResult fetchStatus(@RequestBody(required=false) City record)
    {
        Integer result = 0;
        try
        {
            result = cityService.updateStatusByPrimaryKey(record);
            if (result > 0)
            {
                record = cityService.selectByPrimaryKey(record.getId().intValue());
                return CommonResult.ok(record);
            }
            else
            {
                return CommonResult.fail(record);
            }
        }
        catch (Exception ex)
        {
            return CommonResult.ok(ex);
        }
    }

    /**
     * 获取列表
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/city/fetchList")
    public CommonResult fetchList(@RequestBody(required=false) Param<City> param)
    {
        Integer count = 0;
        List<City> cityList = null;
        try {
            count = cityService.count();
            cityList = cityService.selectAll(param);
        }
        catch (NullPointerException ex)
        {
            System.out.println("**********::" + ex.toString());
        }
        TResult tResult = this.getListView(count, cityList, configColumns, this.wjData());
        return CommonResult.ok(tResult);
    }

    /**
     * 外键数据
     * @return
     */
    protected Map<String, Object> wjData()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("provinceId", provinceService.getTKVAll());
        return map;
    }

    protected List<TColumn> getColumn()
    {
        // 字段
        List<TColumn> tColumnList = new ArrayList<>();
        TColumn tc = new TColumn();
        tc.setLabel("名称");
        tc.setProp("name");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("省市");
        tc.setProp("provinceId");
        tc.setType(1);
        tc.setOptions(provinceService.getTKVAll());
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("状态");
        tc.setProp("status");
        tc.setType(1);
        tc.setOptions(this.getStatus());
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("创建时间");
        tc.setProp("createTime");
        tc.setType(5);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("更新时间");
        tc.setProp("updateTime");
        tc.setType(5);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("备注");
        tc.setProp("remark");
        tc.setType(0);
        tColumnList.add(tc);
        return tColumnList;
    }

    protected List<String> getSearch()
    {
        // 查询字段
        List<String> fColumnList = new ArrayList<>();
//        fColumnList.add("username");
        fColumnList.add("status");
        return fColumnList;
    }

}
