package com.megazone.act.cms.domain;

import com.megazone.act.cms.domain.type.ContractStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "cntrct_ver")
    private Double version = 1.0;

    @Column(name = "cntrct_nm")
    private String name;

    @Column(name = "rmrk")
    private String description;

    @JoinColumn(name = "crprtn_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Corporation corporation;

    @JoinColumn(name = "cstmr_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BusinessPartner businessPartner;

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

    @Enumerated(EnumType.STRING)
    private ContractStatus status = ContractStatus.SAVED;

    @Column(name = "src_system_ref_id")
    private String salesForceContractId;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "contract")
    private List<ContractDetail> contractDetails = new ArrayList<>();

    public Contract(String name, String description, String contractorName) {
        this(name, description, null, null, null, null, null, contractorName, null, null);
    }

    @Builder
    public Contract(
        String name,
        String description,
        Corporation corporation,
        BusinessPartner businessPartner,
        ContractTypes contractTypes,
        ContractPeriod period,
        ContractMoney contractMoney,
        String contractorName,
        String salesPersonName,
        String salesForceContractId
    ) {
        this.name = name;
        this.description = description;
        this.corporation = corporation;
        this.businessPartner = businessPartner;
        this.contractTypes = contractTypes;
        this.period = period;
        this.contractMoney = contractMoney;
        this.contractorName = contractorName;
        this.salesPersonName = salesPersonName;
        this.salesForceContractId = salesForceContractId;
    }

    public void update(String name, String contents) {
        this.name = name;
        this.description = contents;
    }

    public void addContractDetails(List<ContractDetail> contractDetails) {
        contractDetails.forEach(this::addContractDetail);
    }

    private void addContractDetail(ContractDetail contractDetail) {
        contractDetail.setContract(this);
        contractDetails.add(contractDetail);
    }
}
