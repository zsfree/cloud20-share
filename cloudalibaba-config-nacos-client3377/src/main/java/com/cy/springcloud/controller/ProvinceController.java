package com.cy.springcloud.controller;

import com.cy.springcloud.entities.Province;
import com.cy.springcloud.entities.common.*;
import com.cy.springcloud.service.AreaService;
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
 * @ClassName ProvinceController
 * @Description TODO
 * @Author zs
 * @Date 2020/4/10 23:59
 * @Version 1.0
 **/
@RestController
@Slf4j
@RefreshScope
public class ProvinceController extends BaseController
{
    @Resource
    ProvinceService provinceService;
    @Resource
    AreaService areaService;

    @Value("${config.columns.province}")
    protected String configColumns;

    /**
     * 删除数据
     * @param record
     * @return
     */
    @PostMapping(value = "/province/fetchDelete")
    public CommonResult fetchDelete(@RequestBody(required=false) Province record)
    {
        Integer result = 0;
        try {
            result = provinceService.deleteByPrimaryKey(record.getId().intValue());
            return CommonResult.ok(result);
        }
        catch (Exception ex) {
            return CommonResult.fail(ex);
        }
    }

    /**
     * 新增数据
     * @param record
     * @return
     */
    @PostMapping(value = "/province/fetchAdd")
    public CommonResult fetchAdd(@RequestBody(required=false) Province record)
    {
        Integer result = 0;
        try
        {
            result = provinceService.insert(record);
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
    @PostMapping(value = "/province/fetchEdit")
    public CommonResult fetchEdit(@RequestBody(required=false) Province record)
    {
        Integer result = 0;
        try
        {
            result = provinceService.updateByPrimaryKey(record);
            return CommonResult.ok(result);
        }
        catch (Exception ex)
        {
            return CommonResult.fail(ex);
        }

    }

    /**
     * 更新操作状态
     * @param record
     * @return
     */
    @PostMapping(value = "/province/fetchStatus")
    public CommonResult fetchStatus(@RequestBody(required=false) Province record)
    {
        Integer result = 0;
        try
        {
            result = provinceService.updateStatusByPrimaryKey(record);
            if (result > 0)
            {
                record = provinceService.selectByPrimaryKey(record.getId().intValue());
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
    @PostMapping(value = "/province/fetchList")
    public CommonResult fetchList(@RequestBody(required=false) Param<Province> param)
    {
        Integer count = 0;
        List<Province> provinceList = null;
        try {
            count = provinceService.count();
            provinceList = provinceService.selectAll(param);
        }
        catch (NullPointerException ex)
        {
            System.out.println("**********::" + ex.toString());
        }
        TResult tResult = this.getListView(count, provinceList, configColumns, this.wjData());
        return CommonResult.ok(tResult);
    }

    /**
     * 外键数据
     * @return
     */
    public Map<String, Object> wjData()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("areaId", areaService.getTKVAll());
        return map;
    }

    /**
     * 当注册中心nacos中配置为null时本地设置方可用
     * @return
     */
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
        tc.setLabel("地区");
        tc.setProp("areaId");
        tc.setType(1);
        tc.setOptions(areaService.getTKVAll());
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
