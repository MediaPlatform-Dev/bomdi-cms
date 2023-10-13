package com.megazone.act.cms.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Audited
@Table(name = "tb_crprtn_m")
@Entity
public class Corporation extends AuditingFields {

    @Id @GeneratedValue
    @Column(name = "crprtn_id")
    private Long id;

    @Column(name = "crprtn_nm")
    private String name;

    public Corporation(String name) {
        this.name = name;
    }
}
