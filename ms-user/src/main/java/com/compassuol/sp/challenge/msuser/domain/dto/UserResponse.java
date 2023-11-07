package com.compassuol.sp.challenge.msuser.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class UserRequest {
    @Size(min = 3, message = "Need have at least 3 chars")
    private String firstName;
    @Size(min = 3, message = "Need have at least 3 chars")
    private String lastName;
    @Size(min = 14, max = 14, message = "Cpf must have 14 chars")
    @Pattern(regexp = "^[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}$", message = "Cpf need respect the pattern 000.000.000-00")
    private String cpf;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Bday not be null")
    @Past(message = "Bday must be in the past")
    private Date birthdate;
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @Size(min = 6)
    private String password;
    @NotNull
    private Boolean active;
}