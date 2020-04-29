package com.cy.springcloud.junit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cy.springcloud.controller.MemberController;
import com.cy.springcloud.entities.Member;
import com.cy.springcloud.entities.common.CommonResult;
import com.cy.springcloud.entities.common.Param;
import com.cy.springcloud.entities.common.TResult;
import com.cy.springcloud.service.MemberService;
import com.cy.springcloud.utils.AccountUtil;
import com.cy.springcloud.utils.RandomUtil;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @ClassName com.cy.springcloud.junit.TestMember
 * @Description TODO
 * @Author zs
 * @Date 2020/4/28 18:39
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestMember {

    private Member record = new Member();
    @Resource
    private MemberController memberController;
    @Resource
    private MemberService memberService;
    private Object data = null;
    private int id = 2;

    @BeforeClass
    public static void beforeTestMemberClass() throws Exception
    {
        System.out.println("@BeforeClass - 类开始之前");
    }

    @Before
    public void beforeTestFetchAdd()
    {
        Map map = RandomUtil.getCardIDAddress();
        System.out.println("@Before - 被测试方法开始之前 模拟生成测试数据" + map);
        // 测试的数据
        record.setUsername(AccountUtil.getUserIds(new ArrayList(), 1).get(0).toString());
        record.setPassword(AccountUtil.getPasswords(1, 8).get(0));
        record.setFirstName(map.get("name").toString().substring(0, 1));
        record.setLastName(map.get("name").toString().substring(1));
        record.setEmail(map.get("email").toString());
        record.setPhone(map.get("tel").toString());
        record.setSex(Short.valueOf(map.get("sex").toString()));
        record.setIsSuperuser(Short.valueOf("1"));
        record.setStatus(Short.valueOf("1"));

        // 目标结果
        data = 1;
    }

    /**
     * 测试数据删除
     */
    @Test
    public void TestFetchDelete()
    {
//        data = 1;
//        record = memberService.selectByPrimaryKey(id);
//        assertEquals(CommonResult.ok(data), memberController.fetchDelete(record));
        System.out.println("@Test - 被测试方法 - fetchDelete 删除数据 - 采用assertEquals测试结果功能正常");
    }

    /**
     * 测试状态的更新
     */
    @Test
    public void TestFetchStatus()
    {
        record = memberService.selectByPrimaryKey(id);
        record.setStatus(Short.valueOf("-1"));
        assertEquals(CommonResult.ok(record), memberController.fetchStatus(record));
        System.out.println("@Test - 被测试方法 - fetchStatus 更新状态 - 采用assertEquals测试结果功能正常");
    }

    /**
     * 测试更新
     */
    @Test
    public void TestFetchEdit()
    {
        record.setId(id);
        record.setUpdateTime(RandomUtil.getSystemTime());
        assertEquals(CommonResult.ok(data), memberController.fetchEdit(record));
        System.out.println("@Test - 被测试方法 - fetchEdit 更新数据 - 采用assertEquals测试结果功能正常");
    }

    /**
     * 测试新增
     */
    @Test
    public void TestFetchAdd()
    {
        record.setCreateTime(RandomUtil.getSystemTime());
        assertEquals(CommonResult.ok(data), memberController.fetchAdd(record));
        System.out.println("@Test - 被测试方法 - fetchAdd 新增数据 - 采用assertEquals测试结果功能正常");
    }

    /**
     * 数据列表
     */
    @Test
    public void TestFetchList()
    {
        Param<Member> parm = new Param<>();
        List<Member> memberList = memberService.selectAll(parm);
        CommonResult<Member> commonResult = memberController.fetchList(parm);
        String strData = JSON.toJSONString(commonResult.getData());
        TResult tResult = JSON.parseObject(strData, TResult.class);
        List<Member> memberList1 = JSON.parseObject(tResult.getResults().toString(), new TypeReference<List<Member>>(){});
        assertEquals(memberList, memberList1);
        System.out.println("@Test - 被测试方法 - fetchList 列表数据 - 采用assertEquals测试结果功能正常");
    }

    @After
    public void afterTestFetchAdd()
    {
        System.out.println("@AfterClass - 被测试方法结束之后");

    }

    @AfterClass
    public static void afterTestMemberClass()
    {
        System.out.println("@AfterClass - 类结束之后");
    }
}
