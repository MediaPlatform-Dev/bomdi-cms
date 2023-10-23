package com.megazone.act.cms.application.dto.request;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public final class CustomerInfo {

    public static final CustomerInfo EMPTY = new CustomerInfo("", "", "");

    private String name;
    private String email;
    private String etc;
}
