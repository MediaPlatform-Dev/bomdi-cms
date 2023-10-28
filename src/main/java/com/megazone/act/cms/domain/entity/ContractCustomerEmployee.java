package com.megazone.act.cms.domain.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 계약 거래처 직원
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ContractCustomerEmployee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "customer_employee_id")
    @ManyToOne
    private CustomerEmployee customerEmployee;

    public ContractCustomerEmployee(CustomerEmployee customerEmployee) {
        this.customerEmployee = customerEmployee;
    }
}
