package com.megazone.act.cms.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 임직원
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Employee {

    @Id @GeneratedValue
    private Integer id;

    private String name;

    @JoinColumn(name = "department_id")
    @ManyToOne
    private Department department;
}
