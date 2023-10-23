package com.megazone.act.cms.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * 임직원
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JoinColumn(name = "contract_employee_id")
    @OneToMany
    private List<ContractEmployee> contractEmployees;
}
