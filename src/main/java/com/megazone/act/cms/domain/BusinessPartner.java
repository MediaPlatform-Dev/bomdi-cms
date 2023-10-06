package com.megazone.act.cms.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "customer_employee")
@Entity
public class BusinessPartner extends AuditingFields {

    @Id @GeneratedValue
    @Column(name = "cstmr_id")
    private Long id;

    @Column(name = "cstmr_nm")
    private String name;

    public BusinessPartner(String name) {
        this.name = name;
    }
}
