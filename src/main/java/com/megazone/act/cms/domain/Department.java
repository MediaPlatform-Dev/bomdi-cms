package com.megazone.act.cms.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Department {

    @Column(name = "department_id")
    @Id @GeneratedValue
    private Integer id;

    private String name;
}
