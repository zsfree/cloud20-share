package com.cy.springcloud.controller;

import com.cy.springcloud.entities.*;
import com.cy.springcloud.entities.common.*;
import com.cy.springcloud.service.MemberService;
import com.cy.springcloud.utils.MultipartFileToFileUtils;
import com.cy.springcloud.utils.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
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
public class MemberController extends BaseController<Member>
{
    @Resource
    MemberService memberService;

    @Value("${config.columns.member}")
    protected String configColumns;

    /**
     * 删除数据
     * @param record
     * @return
     */
    @PostMapping(value = "/member/fetchDelete")
    public CommonResult fetchDelete(@RequestBody(required=false) Member record)
    {
        Integer result = 0;
        try
        {
            result = memberService.deleteByPrimaryKey(record.getId().intValue());
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
    @PostMapping(value = "/member/fetchAdd")
    public CommonResult fetchAdd(@RequestBody(required=false) Member record)
    {
        Integer result = 0;
        try
        {
            result = memberService.insert(record);
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
    @PostMapping(value = "/member/fetchEdit")
    public CommonResult fetchEdit(@RequestBody(required=false) Member record)
    {
        Integer result = 0;
        try
        {
            result = memberService.updateByPrimaryKey(record);
            return CommonResult.ok(result);
        } catch (Exception ex)
        {
            return CommonResult.fail(ex.toString());
        }
    }

    /**
     * 更新操作状态
     * @param record
     * @return
     */
    @PostMapping(value = "/member/fetchStatus")
    public CommonResult fetchStatus(@RequestBody(required=false) Member record)
    {
        Integer result = 0;
        try
        {
            result = memberService.updateStatusByPrimaryKey(record);
            if (result > 0)
            {
                record = memberService.selectByPrimaryKey(record.getId());
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
        TResult tResult = this.getListView(count, memberList, configColumns, this.wjData());
        return CommonResult.ok(tResult);
    }


    /**
     * 上传图片
     * @param avatar
     * @param key
     * @param token
     * @return
     */
    @PostMapping(value = "/member/fetchUpload")
    public CommonResult fetchUpload(@RequestParam MultipartFile avatar,
                                    @RequestParam(required=false) String key,
                                    @RequestParam(required=false) String token
    ) {
        try {
            //将MultipartFile 转换为File
            File uploadImagefile = MultipartFileToFileUtils.multipartFileToFile(avatar);
            String courseFile = "D:\\nfsdata\\www\\html5\\static\\image";
            String filename = String.valueOf(System.currentTimeMillis());
            UploadUtil uploadUtil = new UploadUtil();
            String fullFileUri = uploadUtil.uploadImage(uploadImagefile, filename, courseFile);
            System.out.println("*************::"+filename);
            System.out.println("*************::"+fullFileUri);
            Map map = new HashMap();
            map.put("hash", fullFileUri);
            map.put("key", uploadUtil.getFileType());
            return CommonResult.ok(map);
        } catch (Exception ex) {
            return CommonResult.fail(ex.getMessage());
        }
    }

    /**
     * 外键数据
     * @return
     */
    protected Map<String, Object> wjData()
    {
//        Map<String, Object> map = new HashMap<>();
        return null;
    }

    protected List<TColumn> getColumn()
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
