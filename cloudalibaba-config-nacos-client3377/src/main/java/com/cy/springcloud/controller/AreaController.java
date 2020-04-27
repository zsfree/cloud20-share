package com.cy.springcloud.controller;

import com.cy.springcloud.entities.Area;
import com.cy.springcloud.entities.common.*;
import com.cy.springcloud.service.CarService;
import com.cy.springcloud.service.AreaService;
import com.cy.springcloud.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AreaController
 * @Description TODO
 * @Author zs
 * @Date 2020/4/10 23:59
 * @Version 1.0
 **/
@RestController
@Slf4j
public class AreaController extends BaseController
{
    @Resource
    AreaService areaService;

    /**
     * 删除数据
     * @param record
     * @return
     */
    @PostMapping(value = "/area/fetchDelete")
    public CommonResult fetchDelete(@RequestBody(required=false) Area record)
    {
        Integer result = areaService.deleteByPrimaryKey(record.getId().intValue());
        return CommonResult.ok(result);
    }

    /**
     * 新增数据
     * @param record
     * @return
     */
    @PostMapping(value = "/area/fetchAdd")
    public CommonResult fetchAdd(@RequestBody(required=false) Area record)
    {
        Integer result = areaService.insert(record);
        return CommonResult.ok(result);
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    @PostMapping(value = "/area/fetchEdit")
    public CommonResult fetchEdit(@RequestBody(required=false) Area record)
    {
        Integer result = areaService.updateByPrimaryKey(record);
        return CommonResult.ok(result);
    }

    /**
     * 更新操作状态
     * @param record
     * @return
     */
    @PostMapping(value = "/area/fetchStatus")
    public CommonResult fetchStatus(@RequestBody(required=false) Area record)
    {
        areaService.updateStatusByPrimaryKey(record);
        try
        {
            record = areaService.selectByPrimaryKey(record.getId().intValue());
        }
        catch (Exception ex)
        {
            System.out.println("*******::"+ex.toString());
        }
        return CommonResult.ok(record);
    }

    /**
     * 获取列表
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/area/fetchList")
    public CommonResult fetchList(@RequestBody(required=false) Param<Area> param)
    {
        System.out.println(param);
        Integer count = 0;
        List<Area> areaList = null;
        try {
            count = areaService.count();
            areaList = areaService.selectAll(param);
        }
        catch (NullPointerException ex)
        {
            System.out.println("**********::" + ex.toString());
        }
        TResult tResult = this.getListView(count, areaList);
        return CommonResult.ok(tResult);
    }

    /**
     * 获取界面元素，如列表栏目，数据，分页，过滤查询等
     * @param count
     * @param results
     * @return
     */
    private TResult getListView(Integer count, List<Area> results) {
        // 字段
        List<TColumn> tColumnList = this.getColumn();

        // 查询字段
        List<String> FColumnList = this.getSearch();

        // 排序字段
        List<SOptions> sOptionsList = this.getSorts();

        return TResult.format(count, results, tColumnList, FColumnList, sOptionsList, this.getTmpData());
    }

    /**
     * 构建字段栏目,   type 决定了表单组件中栏目的类型 0 input， 1 select, 2 radio, 3 checkbox, 4查找带回 5时间控价 等
     * @return
     */
    private List<TColumn> getColumn()
    {
        // 字段
        List<TColumn> tColumnList = new ArrayList<>();
        TColumn tc = new TColumn();
        tc.setLabel("名称");
        tc.setProp("name");
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

    /**
     * 构建搜索字段
     * @return
     */
    protected List<String> getSearch()
    {
        // 查询字段
        List<String> fColumnList = new ArrayList<>();
//        fColumnList.add("username");
        fColumnList.add("status");
        return fColumnList;
    }

}
