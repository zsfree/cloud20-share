package com.cy.springcloud.service;

import com.cy.springcloud.entities.common.Param;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
    /**
     * 删除操作 根据主键
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(int id);

    /**
     * 添加操作
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<T> selectAll(Param<T> param) throws NullPointerException;

    /**
     * 修改操作
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     * 修改状态操作
     *
     * @param record
     * @return
     */
    int updateStatusByPrimaryKey(T record);

    /**
     * 查询所有数据
     * @return
     */
    int count();

    List<Map> getTKVAll();
}
