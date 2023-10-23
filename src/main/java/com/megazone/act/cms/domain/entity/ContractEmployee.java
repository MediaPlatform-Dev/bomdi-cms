package com.megazone.act.cms.domain.entity;

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

    @JoinColumn(name = "contract_id")
    @Setter @ManyToOne
    private Contract contract;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    public ContractEmployee(Employee employee) {
        this.employee = employee;
    }
}
