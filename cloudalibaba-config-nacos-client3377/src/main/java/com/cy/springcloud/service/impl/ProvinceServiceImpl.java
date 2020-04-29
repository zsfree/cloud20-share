package com.cy.springcloud.service.impl;

import com.cy.springcloud.dao.ProvinceMapper;
import com.cy.springcloud.entities.Province;
import com.cy.springcloud.entities.common.Param;
import com.cy.springcloud.service.ProvinceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProvinceServiceImpl implements ProvinceService
{

    @Resource
    private ProvinceMapper provinceMapper;

    @Override
    public int deleteByPrimaryKey(int id) {
        return provinceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Province record) {
        return provinceMapper.insert(record);
    }

    @Override
    public Province selectByPrimaryKey(int id) {
        return provinceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Province> selectAll(Param<Province> param)
    {
        String strWhere = "";
        String strCloumn = "";
        if (param.getCloumn() != null)
        {
            strCloumn = param.getCloumn();
        }
        Province record = param.getFData();
        if(record != null)
        {
            if (record.getWords() != null)
            {
                strWhere += "";
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
        return provinceMapper.selectAll(strWhere, strCloumn, sort, offset, limit);
    }

    @Override
    public int updateByPrimaryKey(Province record) {
        return provinceMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateStatusByPrimaryKey(Province record) {
        return provinceMapper.updateStatusByPrimaryKey(record);
    }

    @Override
    public int count() {
        return provinceMapper.count();
    }

    @Override
    public List<Map> getTKVAll()
    {
        Param<Province> param = new Param();
        param.setPage(1);
        param.setLimit(50);
        param.setSort("-id");
        param.setCloumn("id, name");
        List<Province> tList = this.selectAll(param);
        System.out.println("**************");
        List<Map> optionsList = new ArrayList<>();
        if (tList != null)
        {
            for (Province tItem: tList)
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
