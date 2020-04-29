package com.cy.springcloud.controller;

import com.cy.springcloud.entities.Car;
import com.cy.springcloud.entities.common.*;
import com.cy.springcloud.service.*;
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
 * @ClassName CarController
 * @Description TODO
 * @Author zs
 * @Date 2020/4/10 23:59
 * @Version 1.0
 **/
@RestController
@Slf4j
@RefreshScope
public class CarController extends BaseController<Car>
{
    @Resource
    CarService carService;
    @Resource
    CorrectiveService correctiveService;
    @Resource
    AreaService areaService;
    @Resource
    ProvinceService provinceService;
    @Resource
    CityService cityService;

    @Value("${config.columns.car}")
    protected String configColumns;


    /**
     * 删除数据
     * @param record
     * @return
     */
    @PostMapping(value = "/car/fetchDelete")
    public CommonResult fetchDelete(@RequestBody(required=false) Car record)
    {
        Integer result = 0;
        try
        {
            result = carService.deleteByPrimaryKey(record.getId().intValue());
            return CommonResult.ok(result);
        }
        catch (Exception ex)
        {
            return CommonResult.fail(ex);
        }
    }

    /**
     * 新增数据
     * @param record
     * @return
     */
    @PostMapping(value = "/car/fetchAdd")
    public CommonResult fetchAdd(@RequestBody(required=false) Car record)
    {
        Integer result = 0;
        try
        {
            result = carService.insert(record);
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
    @PostMapping(value = "/car/fetchEdit")
    public CommonResult fetchEdit(@RequestBody(required=false) Car record)
    {
        Integer result = 0;
        try {
            result = carService.updateByPrimaryKey(record);
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
    @PostMapping(value = "/car/fetchStatus")
    public CommonResult fetchStatus(@RequestBody(required=false) Car record)
    {
        try
        {
            carService.updateStatusByPrimaryKey(record);
            record = carService.selectByPrimaryKey(record.getId().intValue());
            return CommonResult.ok(record);
        }
        catch (Exception ex)
        {
            return CommonResult.fail(ex);
        }
    }

    /**
     * 获取列表
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/car/fetchList")
    public CommonResult fetchList(@RequestBody(required=false) Param<Car> param)
    {
        Integer count = 0;
        List<Car> carList = null;
        try {
            count = carService.count();
            carList = carService.selectAll(param);
        }
        catch (NullPointerException ex)
        {
            System.out.println("**********::" + ex.toString());
        }
        TResult tResult = this.getListView(count, carList, configColumns, this.wjData());
        return CommonResult.ok(tResult);
    }

    /**
     * 外键数据
     * @return
     */
    protected Map<String, Object> wjData()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("areaId", areaService.getTKVAll());
        map.put("provinceId", provinceService.getTKVAll());
        map.put("cityId", cityService.getTKVAll());
        map.put("correctiveId", correctiveService.getTKVAll());
        return map;
    }

    protected List<TColumn> getColumn()
    {
        // 字段
        List<TColumn> tColumnList = new ArrayList<>();
        TColumn tc = new TColumn();
        tc.setLabel("车辆序列码");
        tc.setProp("carSerialNum");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("车辆批次号");
        tc.setProp("carBatch");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("车型");
        tc.setProp("carType");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("地区");
        tc.setProp("areaId");
        tc.setType(1);
        tc.setOptions(areaService.getTKVAll());
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("省市");
        tc.setProp("provinceId");
        tc.setType(1);
        tc.setOptions(provinceService.getTKVAll());
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("城市");
        tc.setProp("cityId");
        tc.setType(1);
        tc.setOptions(cityService.getTKVAll());
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("是否使用");
        tc.setProp("isUse");
        tc.setType(1);
        tc.setOptions(this.getYNStatus());
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("是否故障");
        tc.setProp("isHitch");
        tc.setType(1);
        tc.setOptions(this.getYNStatus());
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("故障ID");
        tc.setProp("hitchId");
        tc.setType(1);
        tc.setOptions(correctiveService.getTKVAll());
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("投入使用时间");
        tc.setProp("useTime");
        tc.setType(5);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("坐标");
        tc.setProp("location");
        tc.setType(0);
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

    protected Map<String, Object> getTmpData ()
    {
        // 关键字段 - 暂未使用
        Map<String, Object> tData = new HashMap<String, Object>();
        tData.put("id", 0);
//        tData.put("car_serial_num", "");
        tData.put("remark", "");
        tData.put("status", 0);
        tData.put("timestamp", 0);
        return tData;
    }

}
