package com.compassuol.sp.challenge.msuser.domain.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private Boolean active;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthdate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date created_at;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updated_at;
}
