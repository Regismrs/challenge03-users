package com.compassuol.sp.challenge.msuser.domain.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserPasswordDto {
    @NotNull(message = "can't be null.")
    @Size(min = 6, message="need have at least 6 characters.")
    private String password;
}
