package com.megazone.act.cms.domain.entity.convertor;

import com.megazone.act.cms.domain.type.SubmissionType;

public class SubmissionTypeConvertor extends CodeEnumConvertor<SubmissionType> {

    @Override
    Class<SubmissionType> supprotClass() {
        return SubmissionType.class;
    }
}
