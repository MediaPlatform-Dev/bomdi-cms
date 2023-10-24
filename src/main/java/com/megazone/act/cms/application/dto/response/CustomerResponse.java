package com.megazone.act.cms.application.dto.response;

import com.megazone.act.cms.domain.entity.Customer;

public record CustomerResponse(

    int id,
    String name

) {

    public static CustomerResponse from(Customer entity) {
        return new CustomerResponse(entity.getId(), entity.getName());
    }
}

