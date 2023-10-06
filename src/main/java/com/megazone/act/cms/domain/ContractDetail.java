package com.megazone.act.cms.domain;

import com.megazone.act.cms.domain.type.ContractStatus;
import com.megazone.act.cms.domain.type.ServiceType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Audited
@Table(name = "TB_CNTRCT_D")
@Entity
public class ContractDetail extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dcntrct_id")
    private Long id;

    @Column(name = "dcntrct_nm")
    private String name;

    @Column(name = "cntrct_ver")
    private Double version;

    @Column(name = "cntrct_no")
    private String number;

    @Column(name = "svc_type_cd")
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Embedded
    private ContractPeriod period;

    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    @JoinColumn(name = "cntrct_id")
    @ManyToOne
    private Contract contract;

    public ContractDetail(String name, Double version, String number, ServiceType serviceType, ContractPeriod period, Contract contract) {
        this.name = name;
        this.version = version;
        this.number = number;
        this.serviceType = serviceType;
        this.period = period;
        this.status = ContractStatus.SAVED;
        this.contract = contract;
    }

    public static ContractDetail from(Contract contract) {
        return new ContractDetail(
            contract.getName(),
            contract.getVersion(),
            contract.getNumber(),
            contract.getContractTypes().getServiceType(),
            contract.getPeriod(),
            contract
        );
    }
}
