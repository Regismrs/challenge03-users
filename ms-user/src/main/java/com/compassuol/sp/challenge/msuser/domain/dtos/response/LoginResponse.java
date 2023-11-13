package com.compassuol.sp.challenge.msuser.domain.dtos.response;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private long expiresIn;
}
