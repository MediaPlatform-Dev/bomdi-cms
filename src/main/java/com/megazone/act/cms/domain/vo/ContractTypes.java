package com.megazone.act.cms.domain.vo;

import com.megazone.act.cms.domain.entity.convertor.*;
import com.megazone.act.cms.domain.type.*;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class ContractTypes {

    @Column(name = "cntrct_gb1_cd")
    @Convert(converter = ContractTypeConvertor.class)
    private ContractType contractType;

    @Column(name = "cntrct_clas_cd")
    @Convert(converter = DealTypeConvertor.class)
    private ContractDetail dealType;

    @Column(name = "src_system_cd")
    @Convert(converter = SubmissionTypeConvertor.class)
    private SubmissionType submissionType;
}
