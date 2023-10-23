package com.megazone.act.cms.domain.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 임직원
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Employee {

    @Column(name = "employee_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JoinColumn(name = "department_id")
    @ManyToOne
    private Department department;
}
