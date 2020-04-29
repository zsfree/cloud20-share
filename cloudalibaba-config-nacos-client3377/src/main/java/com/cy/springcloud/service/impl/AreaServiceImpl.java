package com.cy.springcloud.service.impl;

import com.cy.springcloud.dao.AreaMapper;
import com.cy.springcloud.entities.Area;
import com.cy.springcloud.entities.common.Param;
import com.cy.springcloud.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AreaServiceImpl implements AreaService
{

    @Resource
    private AreaMapper areaMapper;

    @Override
    public int deleteByPrimaryKey(int id) {
        return areaMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Area record) {
        return areaMapper.insert(record);
    }

    @Override
    public Area selectByPrimaryKey(int id) {
        return areaMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Area> selectAll(Param<Area> param)
    {
        String strWhere = "";
        String strCloumn = "";
        if (param.getCloumn() != null)
        {
            strCloumn = param.getCloumn();
        }
        Area record = param.getFData();
        if(record != null)
        {
            if (record.getWords() != null)
            {
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
        return areaMapper.selectAll(strWhere, strCloumn, sort, offset, limit);
    }

    @Override
    public int updateByPrimaryKey(Area record) {
        return areaMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateStatusByPrimaryKey(Area record) {
        return areaMapper.updateStatusByPrimaryKey(record);
    }

    @Override
    public int count() {
        return areaMapper.count();
    }

    @Override
    public List<Map> getTKVAll()
    {
        Param<Area> param = new Param();
        param.setPage(1);
        param.setLimit(50);
        param.setSort("-id");
        param.setCloumn("id, name");
        List<Area> tList = this.selectAll(param);
        System.out.println("**************");
        List<Map> optionsList = new ArrayList<>();
        if (tList != null)
        {
            for (Area tItem: tList)
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
