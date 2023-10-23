package com.megazone.act.cms.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 거래처
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Customer extends AuditingFields {

    @Column(name = "customer_id")
    @Id @GeneratedValue
    private Integer id;

    private String name;

    public Customer(String name) {
        this.name = name;
    }
}
