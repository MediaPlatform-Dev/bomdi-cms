package com.megazone.act.cms.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Audited
@Table(name = "customer_employee")
@Entity
public class Customer extends AuditingFields {

    @Id @GeneratedValue
    @Column(name = "cstmr_id")
    private Long id;

    @Column(name = "cstmr_nm")
    private String name;

    public Customer(String name) {
        this.name = name;
    }
}
