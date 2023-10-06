package com.megazone.act.cms.domain.type;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public final class ClientInfo {

    private ClientType type;
    private String name;
    private String email;
    private String etc;

    public static ClientInfo empty() {
        return new ClientInfo(ClientType.TYPE_1, "", "", "");
    }
}
