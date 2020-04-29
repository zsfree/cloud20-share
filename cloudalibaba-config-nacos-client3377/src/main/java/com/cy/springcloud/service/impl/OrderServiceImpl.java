package com.cy.springcloud.service.impl;

import com.cy.springcloud.dao.OrderMapper;
import com.cy.springcloud.entities.Order;
import com.cy.springcloud.entities.common.Param;
import com.cy.springcloud.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;

    public int deleteByPrimaryKey(int id) {
        return orderMapper.deleteByPrimaryKey(id);
    }

    public int insert(Order record) {
        return orderMapper.insert(record);
    }

    public Order selectByPrimaryKey(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> selectAll(Param<Order> param)
    {
        String strWhere = "";
        String strCloumn = "";
        if (param.getCloumn() != null)
        {
            strCloumn = param.getCloumn();
        }
        Order record = param.getFData();
        if(record != null)
        {
            if (record.getWords() != null)
            {
                strWhere += (strWhere==""?"":" AND ") + "(" +
                        "occurred like '%" + record.getWords() + "%' OR " +
                        "destination like '%" + record.getWords() + "%' OR " +
                        "remark like '%" + record.getWords() + "%'" +
                        ")";
            }
            if (record.getStatus() != null)
            {
                strWhere += (strWhere==""?"":" AND ") + "status =" + param.getFData().getStatus();
            }
        }
        Integer len = 0;
        String oneStr = "";
        String sort = "";
        if (param.getSort() != null)
        {
            len = param.getSort().length();
            oneStr = param.getSort().substring(0, 1);
            sort = " "+param.getSort().substring(1, len) + " " + (oneStr.equals("-")?"DESC":"ASC");
        }
        Integer offset = 0;
        Integer limit = 10;
        if (param.getPage() != null)
        {
            offset = (param.getPage()-1)*param.getLimit();
            limit = param.getLimit();
        }
        return orderMapper.selectAll(strWhere, strCloumn, sort, offset, limit);
    }

    public int updateByPrimaryKey(Order record) {
        return orderMapper.updateByPrimaryKey(record);
    }
    
    public int updateStatusByPrimaryKey(Order record) {
        return orderMapper.updateStatusByPrimaryKey(record);
    }

    public int count() {
        return orderMapper.count();
    }

    @Override
    public List<Map> getTKVAll()
    {
        Param<Order> param = new Param();
        param.setPage(1);
        param.setLimit(50);
        param.setSort("-id");
        param.setCloumn("id, status");
        List<Order> tList = this.selectAll(param);
        System.out.println("**************");
        List<Map> optionsList = new ArrayList<>();
        if (tList != null)
        {
            for (Order tItem: tList)
            {
                Map<String, Object> map = new HashMap<>();
                map.put("prop", tItem.getId());
                map.put("label", tItem.getStatus());
                optionsList.add(map);
            }
        }
        System.out.println("**************");
        return optionsList;
    }

}
