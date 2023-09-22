package com.megazone.bomdi.cms.domain;

import jakarta.persistence.*;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Contractor extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;

    public Contractor(String name) {
        this.name = name;
        this.phoneNumber = "";
    }
}
