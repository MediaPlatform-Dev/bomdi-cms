package com.megazone.act.cms.domain.type;

import java.util.EnumSet;

public interface CodeEnum {

    String getCode();

    static <T extends Enum<T> & CodeEnum> T of(Class<T> enumClass, String code) {
        return EnumSet.allOf(enumClass)
                .stream()
                .filter(it -> it.getCode().equals(code))
                .findAny()
                .orElseThrow();
    }
}
