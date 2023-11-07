package com.compassuol.sp.challenge.msuser.mapper;

import com.compassuol.sp.challenge.msuser.domain.dto.UserRequest;
import com.compassuol.sp.challenge.msuser.domain.dto.UserResponse;
import com.compassuol.sp.challenge.msuser.domain.entities.User;

import java.util.List;

public class UserMapper {

    public static User toUser(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .cpf(userRequest.getCpf())
                .email(userRequest.getEmail())
                .birthdate(userRequest.getBirthdate())
                .password(userRequest.getPassword())
                .active(userRequest.getActive())
                .build();
    }

    public static UserResponse toDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .cpf(user.getCpf())
                .email(user.getEmail())
                .birthdate(user.getBirthdate())
                .active(user.getActive())
                .build();
    }

}
