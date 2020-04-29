package com.cy.springcloud.service.impl;

import com.cy.springcloud.dao.CustomMapper;
import com.cy.springcloud.entities.Custom;
import com.cy.springcloud.entities.common.Param;
import com.cy.springcloud.service.CustomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomServiceImpl implements CustomService{

    @Resource
    private CustomMapper customMapper;

    public int deleteByPrimaryKey(int id) {
        return customMapper.deleteByPrimaryKey(id);
    }

    public int insert(Custom record) { return customMapper.insert(record); }

    public Custom selectByPrimaryKey(int id) {
        return customMapper.selectByPrimaryKey(id);
    }

    public List<Custom> selectAll(Param<Custom> param) throws NullPointerException
    {
        String strWhere = "";
        String strCloumn = "";
        if (param.getCloumn() != null)
        {
            strCloumn = param.getCloumn();
        }
        Custom record = param.getFData();
        if (record != null)
        {
            if (record.getPhone() != null)
            {
                strWhere += (strWhere==""?"":" AND ") + "phone like '%" + param.getFData().getPhone() + "%'";
            }
            if (record.getName() != null)
            {
                strWhere += (strWhere==""?"":" AND ") + "username like '%" + param.getFData().getName() + "%'";
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
        return customMapper.selectAll(strWhere, strCloumn, sort, offset, limit);
    }


    public int updateByPrimaryKey(Custom record) {
        return customMapper.updateByPrimaryKey(record);
    }

    public int updateStatusByPrimaryKey(Custom record) {
        return customMapper.updateStatusByPrimaryKey(record);
    }

    public int count() {
        return customMapper.count();
    }

    @Override
    public List<Map> getTKVAll()
    {
        Param<Custom> param = new Param();
        param.setPage(1);
        param.setLimit(50);
        param.setSort("-id");
        param.setCloumn("id, phone");
        List<Custom> tList = this.selectAll(param);
        System.out.println("**************");
        List<Map> optionsList = new ArrayList<>();
        if (tList != null)
        {
            for (Custom tItem: tList)
            {
//                memberMap = TransBeamMap.transBean2Map(member);
                Map<String, Object> memberMap = new HashMap<>();
                memberMap.put("prop", tItem.getId());
                memberMap.put("label", tItem.getPhone());
                optionsList.add(memberMap);
            }
        }
        System.out.println("**************");
        return optionsList;
    }

    public Custom login(Custom record) {
        return customMapper.login(record);
    }

}
