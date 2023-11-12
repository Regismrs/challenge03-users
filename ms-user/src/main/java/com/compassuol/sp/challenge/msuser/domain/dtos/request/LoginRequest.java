package com.compassuol.sp.challenge.msuser.domain.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {
    @Email(message = "invalid email address.")
    @NotNull(message = "can't be null.")
    private String email;
    @NotNull(message = "can't be null.")
    private String password; //nao vou avisar que o minimo eh 6

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
