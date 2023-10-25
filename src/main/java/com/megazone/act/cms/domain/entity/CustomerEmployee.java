package com.megazone.act.cms.domain.entity;

import jakarta.persistence.*;

import lombok.*;

/**
 * 거래처 직원
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CustomerEmployee extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;

    public CustomerEmployee(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
