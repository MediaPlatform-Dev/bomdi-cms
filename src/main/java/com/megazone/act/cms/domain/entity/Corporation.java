package com.megazone.act.cms.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tb_crprtn_m")
@Entity
public class Corporation extends AuditingFields {

    @Column(name = "crprtn_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "crprtn_nm")
    private String name;

    public Corporation(String name) {
        this.name = name;
    }

    public Corporation(Integer id) {
        this.id = id;
    }
}
