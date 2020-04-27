package com.cy.springcloud.dao;

import com.cy.springcloud.entities.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {

    Member login(Member record);
}