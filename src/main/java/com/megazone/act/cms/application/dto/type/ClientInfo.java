package com.megazone.act.cms.application.dto.type;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public final class ClientInfo {

    private ClientType type;
    private String name;
    private String email;
    private String etc;
}
