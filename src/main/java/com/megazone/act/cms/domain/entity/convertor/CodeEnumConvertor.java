package com.megazone.act.cms.domain.entity.convertor;

import com.megazone.act.cms.domain.type.CodeEnum;
import jakarta.persistence.AttributeConverter;

abstract class CodeEnumConvertor<T extends Enum<T> & CodeEnum> implements AttributeConverter<T, String> {

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return attribute.getCode();
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        return CodeEnum.of(supprotClass(), dbData).orElseGet(this::defaultType);
    }

    abstract Class<T> supprotClass();

    abstract T defaultType();
}
