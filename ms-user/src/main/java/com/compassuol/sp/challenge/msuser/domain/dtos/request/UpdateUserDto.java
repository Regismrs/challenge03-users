package com.compassuol.sp.challenge.msuser.domain.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
public class UpdateUserDto {
    @NotBlank(message = "can't be null.")
    @Size(min = 3, message = "need have at least 3 characters.")
    private String firstName;

    @NotBlank(message = "can't be null")
    @Size(min = 3, message = "need have at least 3 characters.")
    private String lastName;

    @NotBlank(message = "can't be null")
    @Size(min = 14, max = 14, message = "must have 14 characters.")
    @Pattern(regexp = "^[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}$", message = "need respect the pattern 000.000.000-00.")
    private String cpf;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "bday can't be null.")
    @Past(message = "bday must be a past date.")
    private Date birthdate;

    @Email(message = "invalid email address.")
    private String email;

    @NotNull(message = "can't be null")
    private Boolean active;
}