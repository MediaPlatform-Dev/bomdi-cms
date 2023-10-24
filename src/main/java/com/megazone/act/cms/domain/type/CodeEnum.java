package com.megazone.act.cms.domain.type;

import java.util.EnumSet;
import java.util.Optional;

public interface CodeEnum {

    String getCode();

    static <T extends Enum<T> & CodeEnum> Optional<T> of(Class<T> enumClass, String code) {
        return EnumSet.allOf(enumClass)
                .stream()
                .filter(it -> it.getCode().equals(code))
                .findAny();
    }
}
