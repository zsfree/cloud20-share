package com.cy.springcloud.controller;

import com.cy.springcloud.entities.common.CommonResult;
import com.cy.springcloud.entities.Order;
import com.cy.springcloud.entities.common.Param;
import com.cy.springcloud.entities.common.SOptions;
import com.cy.springcloud.entities.common.TColumn;
import com.cy.springcloud.entities.common.TResult;
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
 * @ClassName LoginController
 * @Description TODO
 * @Author zs
 * @Date 2020/4/10 23:59
 * @Version 1.0
 **/
@RestController
@Slf4j
@RefreshScope
public class OrderController extends BaseController<Order>
{
    @Resource
    OrderService orderService;
    @Resource
    CustomService customService;
    @Resource
    CarService carService;
    @Resource
    CityService cityService;
    @Resource
    MemberService memberService;

    @Value("${config.columns.order}")
    protected String configColumns;

    /**
     * 删除数据
     * @param record
     * @return
     */
    @PostMapping(value = "/order/fetchDelete")
    public CommonResult fetchDelete(@RequestBody(required=false) Order record)
    {
        Integer result = 0;
        try
        {
            result = orderService.deleteByPrimaryKey(record.getId().intValue());
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
    @PostMapping(value = "/order/fetchAdd")
    public CommonResult fetchAdd(@RequestBody(required=false) Order record)
    {
        Integer result = 0;
        try
        {
            result = orderService.insert(record);
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
    @PostMapping(value = "/order/fetchEdit")
    public CommonResult fetchEdit(@RequestBody(required=false) Order record)
    {
        Integer result = 0;
        try {
            result = orderService.updateByPrimaryKey(record);
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
    @PostMapping(value = "/order/fetchStatus")
    public CommonResult fetchStatus(@RequestBody(required=false) Order record)
    {
        Integer result = 0;
        try
        {
            result = orderService.updateStatusByPrimaryKey(record);
            if (result > 0)
            {
                record = orderService.selectByPrimaryKey(record.getId().intValue());
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
    @PostMapping(value = "/order/fetchList")
    public CommonResult fetchList(@RequestBody(required=false) Param<Order> param)
    {
        Integer count = 0;
        List<Order> memberList = null;
        try {
            count = orderService.count();
            memberList = orderService.selectAll(param);
        }
        catch (NullPointerException ex)
        {
            System.out.println("**********::" + ex.toString());
        }
        TResult tResult = this.getListView(count, memberList, configColumns, this.wjData());
        return CommonResult.ok(tResult);
    }

    /**
     * 外键数据
     * @return
     */
    protected Map<String, Object> wjData()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("customId", customService.getTKVAll());
        map.put("carId", carService.getTKVAll());
        map.put("cityId", cityService.getTKVAll());
        map.put("memberId", memberService.getTKVAll());
        return map;
    }

    protected List<TColumn> getColumn()
    {
        // 字段
        List<TColumn> tColumnList = new ArrayList<>();
        TColumn tc = new TColumn();
        tc.setLabel("订单流水");
        tc.setProp("orderNo");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("客户");
        tc.setProp("customId");
        tc.setType(1);
        tc.setOptions(customService.getTKVAll());
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("车辆");
        tc.setProp("carId");
        tc.setType(1);
        tc.setOptions(carService.getTKVAll());
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("单价");
        tc.setProp("price");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("时长");
        tc.setProp("useDuration");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("总额");
        tc.setProp("total");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("用车时间");
        tc.setProp("useCarTime");
        tc.setType(5);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("还车时间");
        tc.setProp("returnCarTime");
        tc.setType(5);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("付款金额");
        tc.setProp("customPay");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("付款时间");
        tc.setProp("payTime");
        tc.setType(5);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("付款类型");
        tc.setProp("payType");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("城市");
        tc.setProp("cityId");
        tc.setType(1);
        tc.setOptions(cityService.getTKVAll());
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("出发地");
        tc.setProp("occurred");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("目的地");
        tc.setProp("destination");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("员工");
        tc.setProp("memberId");
        tc.setType(1);
        tc.setOptions(memberService.getTKVAll());
        tColumnList.add(tc);
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
        fColumnList.add("customId");
        fColumnList.add("status");
        return fColumnList;
    }
}
