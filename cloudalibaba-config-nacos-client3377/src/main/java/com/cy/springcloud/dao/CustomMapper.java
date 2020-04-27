package com.cy.springcloud.dao;

import com.cy.springcloud.entities.Custom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomMapper extends BaseMapper<Custom> {

    Custom login(Custom record);
}