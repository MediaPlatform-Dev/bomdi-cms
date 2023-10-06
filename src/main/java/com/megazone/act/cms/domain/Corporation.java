package com.megazone.act.cms.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tb_crprtn_m")
@Entity
public class Corporation extends AuditingFields {

    @Id @GeneratedValue
    @Column(name = "crprtn_sq")
    private Long id;

    @Column(name = "crprtn_nm")
    private String name;

    public Corporation(String name) {
        this.name = name;
    }
}
