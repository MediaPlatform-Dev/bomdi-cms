package com.megazone.act.cms.domain.entity;

import com.megazone.act.cms.domain.entity.convertor.EmployeeRoleTypeConvertor;
import com.megazone.act.cms.domain.type.EmployeeRoleType;
import jakarta.persistence.*;
import lombok.*;

/**
 * 계약 자사 직원
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ContractEmployee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = EmployeeRoleTypeConvertor.class)
    private EmployeeRoleType type;

    @JoinColumn(name = "contract_id")
    @Setter @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    public ContractEmployee(Employee employee, EmployeeRoleType type) {
        this.employee = employee;
        this.type = type;
    }
}
