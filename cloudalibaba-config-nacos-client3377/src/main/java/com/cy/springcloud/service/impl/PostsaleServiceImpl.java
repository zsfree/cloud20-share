package com.cy.springcloud.service.impl;

import com.cy.springcloud.dao.PostsaleMapper;
import com.cy.springcloud.dao.PostsaleMapper;
import com.cy.springcloud.entities.Postsale;
import com.cy.springcloud.entities.common.Param;
import com.cy.springcloud.service.PostsaleService;
import com.cy.springcloud.service.PostsaleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostsaleServiceImpl implements PostsaleService
{

    @Resource
    private PostsaleMapper postsaleMapper;

    @Override
    public int deleteByPrimaryKey(int id) {
        return postsaleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Postsale record) {
        return postsaleMapper.insert(record);
    }

    @Override
    public Postsale selectByPrimaryKey(int id) {
        return postsaleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Postsale> selectAll(Param<Postsale> param)
    {
        String strWhere = "";
        String strCloumn = "";
        if (param.getCloumn() != null)
        {
            strCloumn = param.getCloumn();
        }
        Postsale record = param.getFData();
        if(record != null)
        {
            if (record.getWords() != null)
            {
                strWhere += (strWhere==""?"":" AND ") + "remark like '%" + record.getWords() + "%'";
            }
            if (record.getStatus() != null)
            {
                strWhere += (strWhere==""?"":" AND ") + "status =" + record.getStatus();
            }
        }

        Integer len = param.getSort().length();
        String oneStr = param.getSort().substring(0, 1);
        String sort = ""+param.getSort().substring(1, len) + " " + (oneStr.equals("-")?"DESC":"ASC");
        return postsaleMapper.selectAll(strWhere, strCloumn, sort, (param.getPage()-1)*param.getLimit(), param.getLimit());
    }

    @Override
    public int updateByPrimaryKey(Postsale record) {
        return postsaleMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateStatusByPrimaryKey(Postsale record) {
        return postsaleMapper.updateStatusByPrimaryKey(record);
    }

    @Override
    public int count() {
        return postsaleMapper.count();
    }

    @Override
    public List<Map> getTKVAll()
    {
        Param<Postsale> param = new Param();
        param.setPage(1);
        param.setLimit(50);
        param.setSort("-id");
        param.setCloumn("id, service_progress");
        List<Postsale> tList = this.selectAll(param);
        System.out.println("**************");
        List<Map> optionsList = new ArrayList<>();
        if (tList != null)
        {
            for (Postsale tItem: tList)
            {
//                memberMap = TransBeamMap.transBean2Map(member);
                Map<String, Object> map = new HashMap<>();
                map.put("prop", tItem.getId());
                map.put("label", tItem.getServiceProgress());
                optionsList.add(map);
            }
        }
        System.out.println("**************");
        return optionsList;
    }



}
