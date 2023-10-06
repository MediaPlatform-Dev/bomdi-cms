package com.megazone.act.cms.domain;

import com.megazone.act.cms.domain.type.ContractStatus;
import jakarta.persistence.*;

import lombok.*;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Audited
@Table(name = "tb_cntrct_m")
@Entity
public class Contract extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cntrct_id")
    private Long id;

    @Column(name = "cntrct_no")
    private String number;

    @Builder.Default
    @Column(name = "cntrct_ver")
    private Double version = 1.0;

    @Column(name = "cntrct_nm")
    private String name;
    @Column(name = "rmrk")
    private String description;

    @Audited(targetAuditMode = NOT_AUDITED)
    @JoinColumn(name = "crprtn_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Corporation corporation;
    @Column(name = "crprtn_nm")
    private String corporationName;

    @Audited(targetAuditMode = NOT_AUDITED)
    @JoinColumn(name = "cstmr_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BusinessPartner businessPartner;
    @Column(name = "cstmr_nm")
    private String businessPartnerName;

    @Embedded
    private ContractTypes contractTypes;

    @Embedded
    private ContractPeriod period;

    @Embedded
    private ContractMoney contractMoney;

    @Column(name = "cntrct_chrgr_nm")
    private String contractorName;

    @Column(name = "sales_chrgr_nm")
    private String salesPersonName;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private ContractStatus status = ContractStatus.SAVED;


    @Column(name = "src_system_ref_id")
    private String salesForceContractId;

    @Builder.Default
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "contract")
    private List<ContractDetail> contractDetails = new ArrayList<>();

    public Contract(String name, String description, String contractorName) {
        this.name = name;
        this.description = description;
        this.contractorName = contractorName;
    }

    public void update(String name, String contents) {
        this.name = name;
        this.description = contents;
    }

    public void copyContractDetails() {
        contractDetails.add(ContractDetail.from(this));
    }
}
