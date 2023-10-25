package com.megazone.act.cms.application.dto.request;

import com.megazone.act.cms.domain.entity.ContractCustomerEmployee;
import com.megazone.act.cms.domain.entity.CustomerEmployee;

public record ContractCustomerEmployeeResponse(
    String name,
    String email
) {

    public static ContractCustomerEmployeeResponse from(ContractCustomerEmployee entity) {
        CustomerEmployee employee = entity.getCustomerEmployee();
        return new ContractCustomerEmployeeResponse(employee.getName(), employee.getEmail());
    }
}
