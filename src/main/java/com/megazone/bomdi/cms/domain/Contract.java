package com.megazone.bomdi.cms.domain;

import jakarta.persistence.*;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Contract extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contents;

    @JoinColumn(name = "contractor_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Contractor contractor;

    public Contract(String name, String contents, Contractor contractor) {
        this.name = name;
        this.contents = contents;
        this.contractor = contractor;
    }

    public void update(String name, String contents) {
        this.name = name;
        this.contents = contents;
    }
}
