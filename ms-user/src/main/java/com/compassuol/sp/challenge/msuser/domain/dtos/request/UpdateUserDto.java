package com.compassuol.sp.challenge.msuser.domain.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
public class UpdateUserDto {
    @NotBlank(message = "can't be null")
    @Size(min = 3, message = "need have at least 3 chars")
    private String firstName;

    @NotBlank(message = "can't be null")
    @Size(min = 3, message = "need have at least 3 chars")
    private String lastName;

    @Size(min = 14, max = 14, message = "must have 14 chars")
    @Pattern(regexp = "^[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}$", message = "need respect the pattern 000.000.000-00")
    private String cpf;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Bday not be null")
    @Past(message = "Bday must be in the past")
    private Date birthdate;

    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "is invalid")
    private String email;

    @NotNull
    private Boolean active;
}