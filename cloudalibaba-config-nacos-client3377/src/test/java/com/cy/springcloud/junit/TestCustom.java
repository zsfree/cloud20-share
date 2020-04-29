package com.cy.springcloud.junit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cy.springcloud.controller.CustomController;
import com.cy.springcloud.entities.Custom;
import com.cy.springcloud.entities.common.CommonResult;
import com.cy.springcloud.entities.common.Param;
import com.cy.springcloud.entities.common.TResult;
import com.cy.springcloud.service.CustomService;
import com.cy.springcloud.utils.RandomUtil;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @ClassName com.cy.springcloud.junit.TestCustom
 * @Description TODO
 * @Author zs
 * @Date 2020/4/28 18:39
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestCustom {

    private Custom record = new Custom();
    @Resource
    private CustomController customController;
    @Resource
    private CustomService customService;
    private Object data = null;
    private int id = 6;

    @BeforeClass
    public static void beforeTestCustomClass() throws Exception
    {
        System.out.println("@BeforeClass - 类开始之前");
    }

    @Before
    public void beforeTestFetchAdd()
    {
        Map map = RandomUtil.getCardIDAddress();
        System.out.println("@Before - 被测试方法开始之前 模拟生成测试数据" + map);
        // 测试的数据
        record.setName(map.get("name").toString());
        record.setBirth(map.get("birth").toString());
        record.setCreateTime(RandomUtil.getSystemTime());
        record.setPhone(map.get("tel").toString());
        record.setSex((int) map.get("sex"));
        record.setSlat(RandomUtil.getVerifyCode());
        record.setStatus(1);
        record.setRemark("牛人");

        // 目标结果
        data = 1;
    }

    /**
     * 测试状态的更新
     */
    @Test
    public void TestFetchDelete()
    {
//        data = 1;
//        record = customService.selectByPrimaryKey(id);
//        assertEquals(CommonResult.ok(data), customController.fetchDelete(record));
        System.out.println("@Test - 被测试方法 - fetchDelete 删除数据 - 采用assertEquals测试结果功能正常");
    }

    /**
     * 测试状态的更新
     */
    @Test
    public void TestFetchStatus()
    {
        record = customService.selectByPrimaryKey(id);
        record.setStatus(-1);
        assertEquals(CommonResult.ok(record), customController.fetchStatus(record));
        System.out.println("@Test - 被测试方法 - fetchStatus 更新状态 - 采用assertEquals测试结果功能正常");
    }

    /**
     * 测试更新
     */
    @Test
    public void TestFetchEdit()
    {
        record.setId(BigInteger.valueOf(id));
        record.setUpdateTime(RandomUtil.getSystemTime());
        assertEquals(CommonResult.ok(data), customController.fetchEdit(record));
        System.out.println("@Test - 被测试方法 - fetchEdit 更新数据 - 采用assertEquals测试结果功能正常");
    }

    /**
     * 测试新增
     */
    @Test
    public void TestFetchAdd()
    {
        assertEquals(CommonResult.ok(data), customController.fetchAdd(record));
        System.out.println("@Test - 被测试方法 - fetchAdd 新增数据 - 采用assertEquals测试结果功能正常");
    }

    /**
     * 数据列表
     */
    @Test
    public void TestFetchList()
    {
        Param<Custom> parm = new Param<>();
        List<Custom> customList = customService.selectAll(parm);
        CommonResult<Custom> commonResult = customController.fetchList(parm);
        String strData = JSON.toJSONString(commonResult.getData());
        TResult tResult = JSON.parseObject(strData, TResult.class);
        List<Custom> customList1 = JSON.parseObject(tResult.getResults().toString(), new TypeReference<List<Custom>>(){});
        assertEquals(customList, customList1);
        System.out.println("@Test - 被测试方法 - fetchList 列表数据 - 采用assertEquals测试结果功能正常");
    }

    @After
    public void afterTestFetchAdd()
    {
        System.out.println("@AfterClass - 被测试方法结束之后");

    }

    @AfterClass
    public static void afterTestCustomClass()
    {
        System.out.println("@AfterClass - 类结束之后");
    }
}
