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
    private ContractType contractType;

    @Column(name = "cntrct_gb2_cd")
    @Enumerated(EnumType.STRING)
    private ContractDetailType contractDetailType;

    @Column(name = "cntrct_clas_cd")
    @Enumerated(EnumType.STRING)
    private DealType dealType;

    @Column(name = "src_system_cd")
    @Enumerated(EnumType.STRING)
    private SubmissionType submissionType;
}
