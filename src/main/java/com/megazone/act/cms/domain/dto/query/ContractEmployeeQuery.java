package com.megazone.act.cms.domain.dto.query;

import com.megazone.act.cms.domain.type.EmployeeRoleType;
import lombok.Getter;

@Getter
public class ContractEmployeeQuery {
    private int contractId;
    private String name;
    private EmployeeRoleType type;
}
