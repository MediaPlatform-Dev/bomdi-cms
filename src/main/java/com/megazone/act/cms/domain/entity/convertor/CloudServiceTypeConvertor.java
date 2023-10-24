package com.megazone.act.cms.domain.entity.convertor;

import com.megazone.act.cms.domain.type.CloudServiceType;

public class CloudServiceTypeConvertor extends CodeEnumConvertor<CloudServiceType> {

    @Override
    Class<CloudServiceType> supprotClass() {
        return CloudServiceType.class;
    }

    @Override
    CloudServiceType defaultType() {
        return CloudServiceType.AWS;
    }
}
