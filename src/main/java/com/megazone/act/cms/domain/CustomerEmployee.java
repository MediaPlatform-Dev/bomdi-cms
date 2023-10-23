package com.megazone.act.cms.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 거래처 직원
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CustomerEmployee extends AuditingFields {

    @Id @GeneratedValue
    private Integer id;

    private String name;

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;

    public CustomerEmployee(String name) {
        this.name = name;
    }
}
