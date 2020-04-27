package com.cy.springcloud.controller;

import com.cy.springcloud.entities.common.CommonResult;
import com.cy.springcloud.entities.Custom;
import com.cy.springcloud.entities.common.Param;
import com.cy.springcloud.entities.common.SOptions;
import com.cy.springcloud.entities.common.TColumn;
import com.cy.springcloud.entities.common.TResult;
import com.cy.springcloud.service.CustomService;
import lombok.extern.slf4j.Slf4j;
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
public class CustomController extends BaseController
{
    @Resource
    CustomService customService;

    /**
     * 删除数据
     * @param record
     * @return
     */
    @PostMapping(value = "/custom/fetchDelete")
    public CommonResult fetchDelete(@RequestBody(required=false) Custom record)
    {
        Integer result = customService.deleteByPrimaryKey(record.getId().intValue());
        return CommonResult.ok(result);
    }

    /**
     * 新增数据
     * @param record
     * @return
     */
    @PostMapping(value = "/custom/fetchAdd")
    public CommonResult fetchAdd(@RequestBody(required=false) Custom record)
    {
        System.out.println("********"+record);
        Integer result = customService.insert(record);
        return CommonResult.ok(result);
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    @PostMapping(value = "/custom/fetchEdit")
    public CommonResult fetchEdit(@RequestBody(required=false) Custom record)
    {
        Integer result = customService.updateByPrimaryKey(record);
        return CommonResult.ok(result);
    }

    /**
     * 更新操作状态
     * @param record
     * @return
     */
    @PostMapping(value = "/custom/fetchStatus")
    public CommonResult fetchStatus(@RequestBody(required=false) Custom record)
    {
        System.out.println(record);
        customService.updateStatusByPrimaryKey(record);
        try
        {
            record = customService.selectByPrimaryKey(record.getId().intValue());
        }
        catch (Exception ex)
        {
            System.out.println("*******::"+ex.toString());
        }
//
        return CommonResult.ok(record);
    }

    /**
     * 获取列表
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/custom/fetchList")
    public CommonResult fetchList(@RequestBody(required=false) Param<Custom> param)
    {
        Integer count = 0;
        List<Custom> customList = null;
        try {
            count = customService.count();
            customList = customService.selectAll(param);
        }
        catch (NullPointerException ex)
        {
            System.out.println("**********::" + ex.toString());
        }
        TResult tResult = this.getListView(count, customList);
        return CommonResult.ok(tResult);
    }

    /**
     * 获取界面元素，如列表栏目，数据，分页，过滤查询等
     * @param count
     * @param results
     * @return
     */
    private TResult getListView(Integer count, List<Custom> results) {
        // 字段
        List<TColumn> tColumnList = this.getColumn();

        // 查询字段
        List<String> FColumnList = this.getSearch();

        // 排序字段
        List<SOptions> sOptionsList = this.getSorts();

        return TResult.format(count, results, tColumnList, FColumnList, sOptionsList, this.getTmpData());
    }

    /**
     * 构建字段栏目
     * @return
     */
    private List<TColumn> getColumn()
    {
        // 字段
        List<TColumn> tColumnList = new ArrayList<>();
        TColumn tc = new TColumn();
        tc.setLabel("手机");
        tc.setProp("phone");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("名称");
        tc.setProp("name");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("生日");
        tc.setProp("birth");
        tc.setType(5);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("性别");
        tc.setProp("sex");
        tc.setType(2);
        tc.setOptions(this.getSexStatus());
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("随机码");
        tc.setProp("slat");
        tc.setType(0);
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
        tc = new TColumn();
        tc.setLabel("状态");
        tc.setProp("status");
        tc.setType(1);
        tc.setOptions(this.getStatus());
        tColumnList.add(tc);
        return tColumnList;
    }

    /**
     * 构建排序
     * @return
     */
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

        sOptions = new SOptions();
        sOptions.setLabel("按phone升序");
        sOptions.setKey("+phone");
        sOptionsList.add(sOptions);

        sOptions = new SOptions();
        sOptions.setLabel("按phone降序");
        sOptions.setKey("-phone");
        sOptionsList.add(sOptions);

        return sOptionsList;
    }
}
