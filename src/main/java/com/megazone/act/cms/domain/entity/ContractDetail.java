package com.megazone.act.cms.domain.entity;

import com.megazone.act.cms.domain.entity.convertor.ContractDetailTypeConvertor;
import com.megazone.act.cms.domain.entity.convertor.ContractStatusConvertor;
import com.megazone.act.cms.domain.vo.ContractPeriod;
import com.megazone.act.cms.domain.type.*;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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

    @Column(name = "cntrct_gb2_cd")
    @Convert(converter = ContractDetailTypeConvertor.class)
    private ContractDetailType contractDetailType;

    @Column(name = "svc_type_cd")
    @Convert(converter = CloudServiceType.class)
    private CloudServiceType cloudServiceType;

    @Embedded
    private ContractPeriod period;

    @Convert(converter = ContractStatusConvertor.class)
    private ContractStatus status;

    @JoinColumn(name = "cntrct_id")
    @ManyToOne
    private Contract contract;

    public ContractDetail(String name, ContractDetailType type) {
        this(name, 0.0, "", type, null, null);
    }

    public ContractDetail(String name, Double version, String number, ContractDetailType type, CloudServiceType cloudServiceType, ContractPeriod period) {
        this.name = name;
        this.version = version;
        this.number = number;
        this.contractDetailType = type;
        this.cloudServiceType = cloudServiceType;
        this.period = period;
        this.status = ContractStatus.SAVED;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
