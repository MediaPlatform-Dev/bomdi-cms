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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<ContractEmployee> contractEmployees;

    public Employee(String name) {
        this.name = name;
    }
}
