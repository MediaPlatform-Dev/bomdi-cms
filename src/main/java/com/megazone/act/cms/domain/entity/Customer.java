package com.megazone.act.cms.domain.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 거래처
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Customer extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public Customer(Integer customerId) {
        this.id = customerId;
    }
}
