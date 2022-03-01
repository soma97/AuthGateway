package org.unibl.etf.master.security.auth.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse implements BaseResponse {
    private String errorText;
}
