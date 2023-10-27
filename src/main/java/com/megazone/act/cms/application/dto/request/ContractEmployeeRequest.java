package com.megazone.act.cms.application.dto.request;

import com.megazone.act.cms.domain.type.EmployeeRoleType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContractEmployeeRequest {
    private int id;
    private EmployeeRoleType type;
}
