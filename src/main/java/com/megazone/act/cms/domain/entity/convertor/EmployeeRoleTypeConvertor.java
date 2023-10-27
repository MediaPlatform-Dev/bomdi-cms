package com.megazone.act.cms.domain.entity.convertor;

import com.megazone.act.cms.domain.type.EmployeeRoleType;

public class EmployeeRoleTypeConvertor extends CodeEnumConvertor<EmployeeRoleType> {

    @Override
    Class<EmployeeRoleType> supprotClass() {
        return EmployeeRoleType.class;
    }

    @Override
    EmployeeRoleType defaultType() {
        return EmployeeRoleType.CONTRACT;
    }
}
