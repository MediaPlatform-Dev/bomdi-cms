package com.megazone.act.cms.domain;

import com.megazone.act.cms.domain.type.*;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class ContractTypes {

    @Column(name = "cntrct_gb1_cd")
    @Enumerated(EnumType.STRING)
    private ContractDepth1Type contractDepth1Type;
    @Column(name = "cntrct_gb2_cd")
    @Enumerated(EnumType.STRING)
    private ContractDepth2Type contractDepth2Type;
    @Column(name = "cntrct_gb3_cd")
    @Enumerated(EnumType.STRING)
    private ContractDepth3Type contractDepth3Type;

    @Column(name = "cntrct_clas_cd")
    @Enumerated(EnumType.STRING)
    private DealType dealType;

    @Column(name = "svc_type_cd")
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Column(name = "src_system_cd")
    @Enumerated(EnumType.STRING)
    private SubmissionType submissionType;

    public boolean isSales() {
        return contractDepth1Type == ContractDepth1Type.SALES;
    }
}
