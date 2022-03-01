package org.unibl.etf.master.security.anno.gateway.aop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDetailsResponse implements BaseResponse {
    private String username;
    private String role;
}
