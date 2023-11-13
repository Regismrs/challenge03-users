package com.compassuol.sp.challenge.msuser.domain.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserPasswordDto {
    @NotNull(message = "can't be null.")
    @Size(min = 6, message="need have at least 6 characters.")
    private String password;
}
