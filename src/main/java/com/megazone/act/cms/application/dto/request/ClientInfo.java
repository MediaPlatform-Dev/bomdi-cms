package com.megazone.act.cms.application.dto.request;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public final class ClientInfo {

    public static final ClientInfo EMPTY = new ClientInfo("", "", "");

    private String name;
    private String email;
    private String etc;
}
