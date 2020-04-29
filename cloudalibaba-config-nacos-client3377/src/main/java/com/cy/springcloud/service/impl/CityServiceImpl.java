package com.cy.springcloud.service.impl;

import com.cy.springcloud.dao.CityMapper;
import com.cy.springcloud.entities.City;
import com.cy.springcloud.entities.common.Param;
import com.cy.springcloud.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CityServiceImpl implements CityService
{
    @Resource
    private CityMapper cityMapper;

    @Override
    public int deleteByPrimaryKey(int id) {
        return cityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(City record) {
        return cityMapper.insert(record);
    }

    @Override
    public City selectByPrimaryKey(int id) {
        return cityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<City> selectAll(Param<City> param)
    {
        String strWhere = "";
        String strCloumn = "";
        if (param.getCloumn() != null)
        {
            strCloumn = param.getCloumn();
        }
        City record = param.getFData();
        if(record != null)
        {
            if (record.getWords() != null)
            {
                strWhere += " ";
                strWhere += (strWhere==""?"":" AND ") + "(" +
                        "name like '%" + record.getWords() + "%' OR " +
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
        return cityMapper.selectAll(strWhere, strCloumn, sort, offset, limit);
    }

    @Override
    public int updateByPrimaryKey(City record) {
        return cityMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateStatusByPrimaryKey(City record) {
        return cityMapper.updateStatusByPrimaryKey(record);
    }

    @Override
    public int count() {
        return cityMapper.count();
    }

    @Override
    public List<Map> getTKVAll()
    {
        Param<City> param = new Param();
        param.setPage(1);
        param.setLimit(50);
        param.setSort("-id");
        param.setCloumn("id, name");
        List<City> tList = this.selectAll(param);
        System.out.println("**************");
        List<Map> optionsList = new ArrayList<>();
        if (tList != null)
        {
            for (City tItem: tList)
            {
                Map<String, Object> memberMap = new HashMap<>();
                memberMap.put("prop", tItem.getId());
                memberMap.put("label", tItem.getName());
                optionsList.add(memberMap);
            }
        }
        System.out.println("**************");
        return optionsList;
    }

}
