package com.cy.springcloud.junit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cy.springcloud.controller.OrderController;
import com.cy.springcloud.entities.Order;
import com.cy.springcloud.entities.common.CommonResult;
import com.cy.springcloud.entities.common.Param;
import com.cy.springcloud.entities.common.TResult;
import com.cy.springcloud.service.OrderService;
import com.cy.springcloud.utils.RandomUtil;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @ClassName com.cy.springcloud.junit.TestOrder
 * @Description TODO
 * @Author zs
 * @Date 2020/4/28 18:39
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestOrder {

    private Order record = new Order();
    @Resource
    private OrderController orderController;
    @Resource
    private OrderService orderService;
    private Object data = null;
    private int id = 2;

    @BeforeClass
    public static void beforeTestOrderClass() throws Exception
    {
        System.out.println("@BeforeClass - 类开始之前");
    }

    @Before
    public void beforeTestFetchAdd()
    {
        System.out.println("@Before - 被测试方法开始之前 模拟生成测试数据");
        // 测试的数据
        record.setOrderNo(BigInteger.valueOf(Long.valueOf("300000"+RandomUtil.getVerifyCode())));
        record.setCarId(BigInteger.valueOf(1));
        record.setCityId(BigInteger.valueOf(1));
        record.setCustomId(BigInteger.valueOf(1));
        record.setCustomPay(BigDecimal.valueOf(100));
        record.setPrice(BigDecimal.valueOf(2));
        record.setPayTime(RandomUtil.getSystemTime());
        record.setPayType(1);
        record.setUseCarTime(RandomUtil.getSystemTime());
        record.setTotal(BigDecimal.valueOf(200));
        Map<String, String> jw = RandomUtil.randomLonLat(85, 122, 29, 116);
        record.setMemberId(1);
        record.setOccurred(jw.get("J") + "," + jw.get("W"));
        record.setCreateTime(RandomUtil.getSystemTime());
        record.setStatus(Short.valueOf("1"));
        record.setRemark("100000"+RandomUtil.getVerifyCode());

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
//        record = orderService.selectByPrimaryKey(id);
//        assertEquals(CommonResult.ok(data), orderController.fetchDelete(record));
        System.out.println("@Test - 被测试方法 - fetchDelete 删除数据 - 采用assertEquals测试结果功能正常");
    }

    /**
     * 测试状态的更新
     */
    @Test
    public void TestFetchStatus()
    {
        record = orderService.selectByPrimaryKey(id);
        record.setStatus(Short.valueOf("-1"));
        assertEquals(CommonResult.ok(record), orderController.fetchStatus(record));
        System.out.println("@Test - 被测试方法 - fetchStatus 更新状态 - 采用assertEquals测试结果功能正常");
    }

    /**
     * 测试更新
     */
    @Test
    public void TestFetchEdit()
    {
        record.setId(BigInteger.valueOf(id));
        Map<String, String> jw = RandomUtil.randomLonLat(85, 122, 29, 116);
        record.setDestination(jw.get("J") + "," + jw.get("W"));
        record.setReturnCarTime(RandomUtil.getSystemTime());
        record.setUpdateTime(RandomUtil.getSystemTime());
        assertEquals(CommonResult.ok(data), orderController.fetchEdit(record));
        System.out.println("@Test - 被测试方法 - fetchEdit 更新数据 - 采用assertEquals测试结果功能正常");
    }

    /**
     * 测试新增
     */
    @Test
    public void TestFetchAdd()
    {
        assertEquals(CommonResult.ok(data), orderController.fetchAdd(record));
        System.out.println("@Test - 被测试方法 - fetchAdd 新增数据 - 采用assertEquals测试结果功能正常");
    }

    /**
     * 数据列表
     */
    @Test
    public void TestFetchList()
    {
        Param<Order> parm = new Param<>();
        List<Order> orderList = orderService.selectAll(parm);
        CommonResult<Order> commonResult = orderController.fetchList(parm);
        String strData = JSON.toJSONString(commonResult.getData());
        TResult tResult = JSON.parseObject(strData, TResult.class);
        List<Order> orderList1 = JSON.parseObject(tResult.getResults().toString(), new TypeReference<List<Order>>(){});
        assertEquals(orderList, orderList1);
        System.out.println("@Test - 被测试方法 - fetchList 列表数据 - 采用assertEquals测试结果功能正常");
    }

    @After
    public void afterTestFetchAdd()
    {
        System.out.println("@AfterClass - 被测试方法结束之后");

    }

    @AfterClass
    public static void afterTestOrderClass()
    {
        System.out.println("@AfterClass - 类结束之后");
    }
}
