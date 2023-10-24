package com.megazone.act.cms.application.dto.response;

import com.megazone.act.cms.domain.entity.Employee;

public record EmployeeResponse(
    int id,
    String name
) {

    public static EmployeeResponse from(Employee entity) {
        return new EmployeeResponse(entity.getId(), entity.getName());
    }
}
