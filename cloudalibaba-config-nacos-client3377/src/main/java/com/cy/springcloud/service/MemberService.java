package com.cy.springcloud.service;

import com.cy.springcloud.entities.Member;

public interface MemberService extends BaseService<Member> {

    /**
     * 登录
     * @param record
     * @return
     */
    Member login(Member record);
}