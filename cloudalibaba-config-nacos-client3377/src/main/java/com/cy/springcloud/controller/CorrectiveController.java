package com.cy.springcloud.controller;

import com.cy.springcloud.entities.Member;
import com.cy.springcloud.entities.Corrective;
import com.cy.springcloud.entities.common.*;
import com.cy.springcloud.service.CarService;
import com.cy.springcloud.service.MemberService;
import com.cy.springcloud.service.CorrectiveService;
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
 * @ClassName CorrectiveController
 * @Description TODO
 * @Author zs
 * @Date 2020/4/10 23:59
 * @Version 1.0
 **/
@RestController
@Slf4j
public class CorrectiveController extends BaseController
{
    @Resource
    CorrectiveService correctiveService;
    @Resource
    MemberService memberService;
    @Resource
    CarService carService;

    /**
     * 删除数据
     * @param record
     * @return
     */
    @PostMapping(value = "/corrective/fetchDelete")
    public CommonResult fetchDelete(@RequestBody(required=false) Corrective record)
    {
        Integer result = correctiveService.deleteByPrimaryKey(record.getId().intValue());
        return CommonResult.ok(result);
    }

    /**
     * 新增数据
     * @param record
     * @return
     */
    @PostMapping(value = "/corrective/fetchAdd")
    public CommonResult fetchAdd(@RequestBody(required=false) Corrective record)
    {
        Integer result = correctiveService.insert(record);
        return CommonResult.ok(result);
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    @PostMapping(value = "/corrective/fetchEdit")
    public CommonResult fetchEdit(@RequestBody(required=false) Corrective record)
    {
        Integer result = correctiveService.updateByPrimaryKey(record);
        return CommonResult.ok(result);
    }

    /**
     * 更新操作状态
     * @param record
     * @return
     */
    @PostMapping(value = "/corrective/fetchStatus")
    public CommonResult fetchStatus(@RequestBody(required=false) Corrective record)
    {
        correctiveService.updateStatusByPrimaryKey(record);
        try
        {
            record = correctiveService.selectByPrimaryKey(record.getId().intValue());
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
    @PostMapping(value = "/corrective/fetchList")
    public CommonResult fetchList(@RequestBody(required=false) Param<Corrective> param)
    {
        Integer count = 0;
        List<Corrective> correctiveList = null;
        try {
            count = correctiveService.count();
            correctiveList = correctiveService.selectAll(param);
        }
        catch (NullPointerException ex)
        {
            System.out.println("**********::" + ex.toString());
        }
        TResult tResult = this.getListView(count, correctiveList);
        return CommonResult.ok(tResult);
    }

    /**
     * 获取界面元素，如列表栏目，数据，分页，过滤查询等
     * @param count
     * @param results
     * @return
     */
    private TResult getListView(Integer count, List<Corrective> results) {
        // 字段
        List<TColumn> tColumnList = this.getColumn();

        // 查询字段
        List<String> FColumnList = this.getSearch();

        // 排序字段
        List<SOptions> sOptionsList = this.getSorts();

        return TResult.format(count, results, tColumnList, FColumnList, sOptionsList, this.getTmpData());
    }

    private List<TColumn> getColumn()
    {
        // 字段
        List<TColumn> tColumnList = new ArrayList<>();
        TColumn tc = new TColumn();
        tc.setLabel("车辆");
        tc.setProp("carId");
        tc.setType(1);
        tc.setOptions(carService.getTKVAll());
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("方式");
        tc.setProp("types");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("问题");
        tc.setProp("question");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("方案");
        tc.setProp("occurred");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("维修进度");
        tc.setProp("corrProgress");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("员工");
        tc.setProp("memberId");
        tc.setType(1);
        tc.setOptions(memberService.getTKVAll());
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
