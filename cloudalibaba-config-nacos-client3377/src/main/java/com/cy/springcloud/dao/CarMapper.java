package com.cy.springcloud.dao;

import com.cy.springcloud.entities.Car;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarMapper extends BaseMapper<Car>
{

}