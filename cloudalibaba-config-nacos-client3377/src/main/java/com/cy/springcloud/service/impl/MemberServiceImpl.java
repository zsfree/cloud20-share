package com.cy.springcloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cy.springcloud.dao.MemberMapper;
import com.cy.springcloud.entities.Member;
import com.cy.springcloud.entities.common.Param;
import com.cy.springcloud.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService
{

    @Resource
    private MemberMapper memberMapper;

    @Override
    public int deleteByPrimaryKey(int id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Member record) {
        return memberMapper.insert(record);
    }

    @Override
    public Member selectByPrimaryKey(int id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Member> selectAll(Param<Member> param)
    {
        String strWhere = "";
        String strCloumn = "";
        if (param.getCloumn() != null)
        {
            strCloumn = param.getCloumn();
        }
        Member record = param.getFData();
        if(record != null)
        {
            if (record.getPhone() != null)
            {
                strWhere += (strWhere==""?"":" AND ") + "phone like '%" + param.getFData().getPhone() + "%'";
            }
            if (record.getUsername() != null)
            {
                strWhere += (strWhere==""?"":" AND ") + "username like '%" + param.getFData().getUsername() + "%'";
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
        return memberMapper.selectAll(strWhere, strCloumn, sort, offset, limit);
    }

    @Override
    public int updateByPrimaryKey(Member record) {
        return memberMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateStatusByPrimaryKey(Member record) {
        return memberMapper.updateStatusByPrimaryKey(record);
    }

    @Override
    public int count() {
        return memberMapper.count();
    }

    @Override
    public List<Map> getTKVAll()
    {
        Param<Member> param = new Param();
        param.setPage(1);
        param.setLimit(50);
        param.setSort("-id");
        param.setCloumn("id, username");
        List<Member> tList = this.selectAll(param);
        System.out.println("**************");
        List<Map> optionsList = new ArrayList<>();
        if (tList != null)
        {
            for (Member tItem: tList)
            {
                Map<String, Object> map = new HashMap<>();
                map.put("prop", tItem.getId());
                map.put("label", tItem.getUsername());
                optionsList.add(map);
            }
        }
        System.out.println("**************");
        return optionsList;
    }

    @Override
    public Member login(Member record) {
        return memberMapper.login(record);
    }
}
