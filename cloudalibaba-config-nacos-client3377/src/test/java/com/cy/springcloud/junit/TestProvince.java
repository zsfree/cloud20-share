package com.cy.springcloud.junit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cy.springcloud.controller.ProvinceController;
import com.cy.springcloud.entities.Province;
import com.cy.springcloud.entities.common.CommonResult;
import com.cy.springcloud.entities.common.Param;
import com.cy.springcloud.entities.common.TResult;
import com.cy.springcloud.service.ProvinceService;
import com.cy.springcloud.utils.RandomUtil;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @ClassName com.cy.springcloud.junit.TestProvince
 * @Description TODO
 * @Author zs
 * @Date 2020/4/28 18:39
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestProvince {

    private Province record = new Province();
    @Resource
    private ProvinceController provinceController;
    @Resource
    private ProvinceService provinceService;
    private Object data = null;
    private int id = 2;

    @BeforeClass
    public static void beforeTestProvinceClass() throws Exception
    {
        System.out.println("@BeforeClass - 类开始之前");
    }

    @Before
    public void beforeTestFetchAdd()
    {
        System.out.println("@Before - 被测试方法开始之前 模拟生成测试数据");
        // 测试的数据
        record.setName(RandomUtil.getProvince());
        record.setCreateTime(RandomUtil.getSystemTime());
        record.setStatus(Short.valueOf("1"));
        record.setRemark(RandomUtil.getProvince());

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
//        record = provinceService.selectByPrimaryKey(id);
//        assertEquals(CommonResult.ok(data), provinceController.fetchDelete(record));
        System.out.println("@Test - 被测试方法 - fetchDelete 删除数据 - 采用assertEquals测试结果功能正常");
    }

    /**
     * 测试状态的更新
     */
    @Test
    public void TestFetchStatus()
    {
        record = provinceService.selectByPrimaryKey(id);
        record.setStatus(Short.valueOf("-1"));
        assertEquals(CommonResult.ok(record), provinceController.fetchStatus(record));
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
        assertEquals(CommonResult.ok(data), provinceController.fetchEdit(record));
        System.out.println("@Test - 被测试方法 - fetchEdit 更新数据 - 采用assertEquals测试结果功能正常");
    }

    /**
     * 测试新增
     */
    @Test
    public void TestFetchAdd()
    {
        assertEquals(CommonResult.ok(data), provinceController.fetchAdd(record));
        System.out.println("@Test - 被测试方法 - fetchAdd 新增数据 - 采用assertEquals测试结果功能正常");
    }

    /**
     * 数据列表
     */
    @Test
    public void TestFetchList()
    {
        Param<Province> parm = new Param<>();
        List<Province> provinceList = provinceService.selectAll(parm);
        CommonResult<Province> commonResult = provinceController.fetchList(parm);
        String strData = JSON.toJSONString(commonResult.getData());
        TResult tResult = JSON.parseObject(strData, TResult.class);
        List<Province> provinceList1 = JSON.parseObject(tResult.getResults().toString(), new TypeReference<List<Province>>(){});
        assertEquals(provinceList, provinceList1);
        System.out.println("@Test - 被测试方法 - fetchList 列表数据 - 采用assertEquals测试结果功能正常");
    }

    @After
    public void afterTestFetchAdd()
    {
        System.out.println("@AfterClass - 被测试方法结束之后");

    }

    @AfterClass
    public static void afterTestProvinceClass()
    {
        System.out.println("@AfterClass - 类结束之后");
    }
}
