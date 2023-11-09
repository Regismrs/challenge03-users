package com.compassuol.sp.challenge.msuser.domain.dtos.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserPasswordDto {
    @Size(min = 6)
    private String password;
}
