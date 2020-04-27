package com.cy.springcloud.service.impl;

import com.cy.springcloud.dao.CorrectiveMapper;
import com.cy.springcloud.entities.Corrective;
import com.cy.springcloud.entities.Member;
import com.cy.springcloud.entities.common.Param;
import com.cy.springcloud.service.CorrectiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CorrectiveServiceImpl implements CorrectiveService
{

    @Resource
    private CorrectiveMapper correctiveMapper;

    @Override
    public int deleteByPrimaryKey(int id) {
        return correctiveMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Corrective record) {
        return correctiveMapper.insert(record);
    }

    @Override
    public Corrective selectByPrimaryKey(int id) {
        return correctiveMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Corrective> selectAll(Param<Corrective> param)
    {
        String strWhere = "";
        String strCloumn = "";
        if (param.getCloumn() != null)
        {
            strCloumn = param.getCloumn();
        }
        Corrective record = param.getFData();
        if(record != null)
        {
            if (record.getWords() != null)
            {
                strWhere += (strWhere==""?"":" AND ") + "(" +
                        "question like '%" + record.getWords() + "%' OR " +
                        "occurred like '%" + record.getWords() + "%' OR " +
                        "remark like '%" + record.getWords() + "%'" +
                        ")";
            }
//            if (record.getUsername() != null)
//            {
//                strWhere += (strWhere==""?"":" AND ") + "username like '%" + param.getFData().getUsername() + "%'";
//            }
            if (record.getStatus() != null)
            {
                strWhere += (strWhere==""?"":" AND ") + "status =" + param.getFData().getStatus();
            }
        }

        Integer len = param.getSort().length();
        String oneStr = param.getSort().substring(0, 1);
        String sort = ""+param.getSort().substring(1, len) + " " + (oneStr.equals("-")?"DESC":"ASC");
        return correctiveMapper.selectAll(strWhere, strCloumn, sort, (param.getPage()-1)*param.getLimit(), param.getLimit());
    }

    @Override
    public int updateByPrimaryKey(Corrective record) {
        return correctiveMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateStatusByPrimaryKey(Corrective record) {
        return correctiveMapper.updateStatusByPrimaryKey(record);
    }

    @Override
    public int count() {
        return correctiveMapper.count();
    }

    @Override
    public List<Map> getTKVAll()
    {
        Param<Corrective> param = new Param();
        param.setPage(1);
        param.setLimit(50);
        param.setSort("-id");
        param.setCloumn("id, question");
        List<Corrective> tList = this.selectAll(param);
        System.out.println("**************");
        List<Map> optionsList = new ArrayList<>();
        if (tList != null)
        {
            for (Corrective tItem: tList)
            {
                Map<String, Object> memberMap = new HashMap<>();
                memberMap.put("prop", tItem.getId());
                memberMap.put("label", tItem.getQuestion());
                optionsList.add(memberMap);
            }
        }
        System.out.println("**************");
        return optionsList;
    }

}
