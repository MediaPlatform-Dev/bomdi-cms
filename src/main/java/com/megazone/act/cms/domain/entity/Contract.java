package com.megazone.act.cms.domain.entity;

import com.megazone.act.cms.domain.entity.convertor.ContractStatusConvertor;
import com.megazone.act.cms.domain.type.ContractStatus;
import com.megazone.act.cms.domain.vo.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tb_cntrct_m")
@Entity
public class Contract extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cntrct_id")
    private Long id;

    @Column(name = "cntrct_no")
    private String no;

    @Column(name = "cntrct_ver")
    private Double version = 1.0;

    @Column(name = "cntrct_nm")
    private String name;

    @Column(name = "rmrk")
    private String remark;

    @JoinColumn(name = "crprtn_id")
    @ManyToOne
    private Corporation corporation;

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;

    @Embedded
    private ContractTypes contractTypes;

    @Embedded
    private ContractPeriod period;

    @Embedded
    private ContractMoney contractMoney;

    @Convert(converter = ContractStatusConvertor.class)
    private ContractStatus status = ContractStatus.SAVED;

    @Column(name = "src_system_ref_id")
    private String salesForceContractNo;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "contract")
    private List<ContractDetail> contractDetails = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contract")
    private List<ContractEmployee> contractEmployees = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contract")
    private List<ContractCustomerEmployee> contractCustomerEmployees = new ArrayList<>();

    public Contract(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

    public Contract(
        String name,
        String remark,
        String salesForceContractNo,
        Corporation corporation,
        ContractTypes contractTypes,
        ContractPeriod period,
        ContractMoney contractMoney,
        List<ContractEmployee> contractEmployees,
        Customer customer,
        List<ContractCustomerEmployee> contractCustomerEmployees,
        List<ContractDetail> contractDetails
    ) {
        this.name = name;
        this.no = randomNo(contractTypes);
        this.remark = remark;
        this.salesForceContractNo = salesForceContractNo;
        this.corporation = corporation;
        this.contractTypes = contractTypes;
        this.period = period;
        this.contractMoney = contractMoney;
        contractEmployees.forEach(this::addContractEmployee);
        this.customer = customer;
        contractCustomerEmployees.forEach(this::addContractCustomerEmployee);
        contractDetails.forEach(this::addContractDetail);
    }

    private static String randomNo(ContractTypes contractTypes) {
        return String.format("%s-%s-%s",
            "MZC", contractTypes.codeForNo(), UUID.randomUUID().toString().substring(0, 6).toUpperCase());
    }

    private void addContractEmployee(ContractEmployee contractEmployee) {
        contractEmployee.setContract(this);
        this.contractEmployees.add(contractEmployee);
    }

    private void addContractCustomerEmployee(ContractCustomerEmployee contractCustomerEmployee) {
        contractCustomerEmployee.setContract(this);
        this.contractCustomerEmployees.add(contractCustomerEmployee);
    }

    private void addContractDetail(ContractDetail contractDetail) {
        contractDetail.setContract(this);
        contractDetails.add(contractDetail);
    }

    public void update(String name, String contents) {
        this.name = name;
        this.remark = contents;
    }
}
