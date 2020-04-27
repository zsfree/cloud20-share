package com.cy.springcloud.controller;

import com.cy.springcloud.entities.*;
import com.cy.springcloud.entities.common.*;
import com.cy.springcloud.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author zs
 * @Date 2020/4/10 23:59
 * @Version 1.0
 **/
@RestController
@Slf4j
public class MemberController extends BaseController
{
    @Resource
    MemberService memberService;

    /**
     * 删除数据
     * @param record
     * @return
     */
    @PostMapping(value = "/member/fetchDelete")
    public CommonResult fetchDelete(@RequestBody(required=false) Custom record)
    {
        Integer result = memberService.deleteByPrimaryKey(record.getId().intValue());
        return CommonResult.ok(result);
    }

    /**
     * 新增数据
     * @param record
     * @return
     */
    @PostMapping(value = "/member/fetchAdd")
    public CommonResult fetchAdd(@RequestBody(required=false) Member record)
    {
        Integer result = memberService.insert(record);
        return CommonResult.ok(result);
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    @PostMapping(value = "/member/fetchEdit")
    public CommonResult fetchEdit(@RequestBody(required=false) Member record)
    {
        Integer result = memberService.updateByPrimaryKey(record);
        return CommonResult.ok(result);
    }

    /**
     * 更新操作状态
     * @param record
     * @return
     */
    @PostMapping(value = "/member/fetchStatus")
    public CommonResult fetchStatus(@RequestBody(required=false) Member record)
    {
//        member.setUpdateTime((BigInteger) System.currentTimeMillis());
        memberService.updateStatusByPrimaryKey(record);
        record = memberService.selectByPrimaryKey(record.getId());
        return CommonResult.ok(record);
    }

    /**
     * 获取列表
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/member/fetchList")
    public CommonResult fetchList(@RequestBody(required=false) Param<Member> param)
    {
        Integer count = 0;
        List<Member> memberList = null;
        try {
            count = memberService.count();
            memberList = memberService.selectAll(param);
        }
        catch (NullPointerException ex)
        {
            System.out.println("**********::" + ex.toString());
        }
        TResult tResult = this.getListView(count, memberList);
        return CommonResult.ok(tResult);
    }

    /**
     * 获取界面元素，如列表栏目，数据，分页，过滤查询等
     * @param count
     * @param results
     * @return
     */
    private TResult getListView(Integer count, List<Member> results) {
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
        tc.setLabel("手机");
        tc.setProp("phone");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("名称");
        tc.setProp("username");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("邮箱");
        tc.setProp("email");
        tc.setType(0);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("头像");
        tc.setProp("avatar");
        tc.setType(4);
        tColumnList.add(tc);
        tc = new TColumn();
        tc.setLabel("个签");
        tc.setProp("introduction");
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

    protected List<String> getSearch()
    {
        // 查询字段
        List<String> fColumnList = new ArrayList<>();
        fColumnList.add("username");
        fColumnList.add("status");
        return fColumnList;
    }
}
