package com.megazone.act.cms.domain.type;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public final class ClientInfo {

    public static final ClientInfo EMPTY = new ClientInfo(ClientType.TYPE_1, "", "", "");

    private ClientType type;
    private String name;
    private String email;
    private String etc;
}
