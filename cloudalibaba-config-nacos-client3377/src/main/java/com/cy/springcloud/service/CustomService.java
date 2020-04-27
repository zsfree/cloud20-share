package com.cy.springcloud.service;

import com.cy.springcloud.entities.Custom;

public interface CustomService extends BaseService<Custom> {

    /**
     * 登录
     * @param record
     * @return
     */
    Custom login(Custom record);
}