package com.cy.springcloud.service.impl;

import com.cy.springcloud.dao.CarMapper;
import com.cy.springcloud.entities.Car;
import com.cy.springcloud.entities.common.Param;
import com.cy.springcloud.service.CarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService
{

    @Resource
    private CarMapper carMapper;

    @Override
    public int deleteByPrimaryKey(int id) {
        return carMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Car record) {
        return carMapper.insert(record);
    }

    @Override
    public Car selectByPrimaryKey(int id) {
        return carMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Car> selectAll(Param<Car> param)
    {
        String strWhere = "";
        String strCloumn = "";
        if (param.getCloumn() != null)
        {
            strCloumn = param.getCloumn();
        }
        Car record = param.getFData();
        if(record != null)
        {
            if (record.getWords() != null)
            {
                strWhere += (strWhere==""?"":" AND ") + "(" +
                        "car_serial_num like '%" + record.getWords() + "%' OR " +
                        "car_batch like '%" + record.getWords() + "%' OR " +
                        "car_type like '%" + record.getWords() + "%' OR " +
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
        return carMapper.selectAll(strWhere, strCloumn, sort, (param.getPage()-1)*param.getLimit(), param.getLimit());
    }

    @Override
    public int updateByPrimaryKey(Car record) {
        return carMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateStatusByPrimaryKey(Car record) {
        return carMapper.updateStatusByPrimaryKey(record);
    }

    @Override
    public int count() {
        return carMapper.count();
    }

    @Override
    public List<Map> getTKVAll()
    {
        Param<Car> param = new Param();
        param.setPage(1);
        param.setLimit(50);
        param.setSort("-id");
        param.setCloumn("id, car_serial_num");
        List<Car> tList = this.selectAll(param);
        System.out.println("**************");
        List<Map> optionsList = new ArrayList<>();
        if (tList != null)
        {
            for (Car tItem: tList)
            {
//                memberMap = TransBeamMap.transBean2Map(member);
                Map<String, Object> memberMap = new HashMap<>();
                memberMap.put("prop", tItem.getId());
                memberMap.put("label", tItem.getCarSerialNum());
                optionsList.add(memberMap);
            }
        }
        System.out.println("**************");
        return optionsList;
    }

}
