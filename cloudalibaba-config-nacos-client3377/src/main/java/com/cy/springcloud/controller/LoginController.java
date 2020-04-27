package com.cy.springcloud.controller;

import com.cy.springcloud.entities.common.CommonResult;
import com.cy.springcloud.entities.Custom;
import com.cy.springcloud.entities.Member;
import com.cy.springcloud.service.CustomService;
import com.cy.springcloud.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
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
public class LoginController extends BaseController
{
    @Resource
    MemberService memberService;
    @Resource
    CustomService customService;

    /**
     * 管理员登录
     * @param record
     * @return
     */
    @PostMapping(value = "/member/login")
    public CommonResult memberLogin(@RequestBody(required=false) Member record)
    {
        Member member = memberService.login(record);

        if (member == null || !member.getPassword().equals(record.getPassword()))
        {
            return CommonResult.fail(record);
        }

        Map<String, String> map = new HashMap<String,String>();
        map.put("id", member.getId().toString());
        map.put("token", member.getUsername());
        map.put("mSign", member.getSlat());
        return CommonResult.ok(map);
    }

    /**
     * 获取管理员详细
     *
     * @param uid
     * @param token
     * @return
     */
    @GetMapping("/member/info/{uid}/{token}")
    public CommonResult memberInfo(@PathVariable int uid, @PathVariable String token)
    {
        return CommonResult.ok(memberService.selectByPrimaryKey(uid));
    }

    /**
     * 登出
     * @param uid
     * @param token
     * @return
     */
    @GetMapping(value = "/member/logout/{uid}/{token}")
    public CommonResult memberLogout(@PathVariable int uid, @PathVariable String token)
    {
        Custom custom = new Custom();
        custom.setName("张三");
        return CommonResult.ok(custom);
    }

    /**
     * 客户登录
     * @param record
     * @return
     */
    @PostMapping(value = "/custom/login")
    public CommonResult customLogin(@RequestBody(required=false) Custom record)
    {
//        System.out.println(record);
//        record.setPassword(MD5Util.encode(record.getPassword()));
        Custom custom = customService.login(record);

        if (custom == null)
        {
            return CommonResult.fail(record);
        }

        Map<String, String> map = new HashMap<String,String>();
        map.put("mUser", custom.getId().toString());
        map.put("mName", custom.getPhone());
        map.put("mSign", custom.getSlat());
        return CommonResult.ok(map);
    }

    /**
     * 获取详细
     * @param id
     * @return
     */
    @GetMapping(value = "/custom/info/{id}")
    public CommonResult customInfo(@PathVariable int id)
    {
        System.out.println("***********"+"3----------" + id);
        return CommonResult.ok(customService.selectByPrimaryKey(id));
    }

    /**
     * 登出
     * @return
     */
    @PostMapping(value = "/custom/logout")
    public CommonResult customLogout()
    {
        Custom custom = new Custom();
        custom.setName("张三");
        return CommonResult.ok(custom);
    }
}
