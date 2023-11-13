package com.compassuol.sp.challenge.msuser.mapper;

import com.compassuol.sp.challenge.msuser.domain.dtos.request.CreateUserDto;
import com.compassuol.sp.challenge.msuser.domain.dtos.request.UpdateUserDto;
import com.compassuol.sp.challenge.msuser.domain.dtos.response.UserResponse;
import com.compassuol.sp.challenge.msuser.domain.entities.User;

public class UserMapper {

    public static User toUser(CreateUserDto dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .birthdate(dto.getBirthdate())
                .active(true)
                .build();
    }

    public static UserResponse toDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .cpf(user.getCpf())
                .birthdate(user.getBirthdate())
                .email(user.getEmail())
                .active(user.getActive())
                .created_at(user.getCreatedAt())
                .updated_at(user.getUpdatedAt())
                .build();
    }

    public static User applyDtoToUser(UpdateUserDto dto, User user) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setCpf(dto.getCpf());
        user.setBirthdate(dto.getBirthdate());
        user.setEmail(dto.getEmail());
        user.setActive(dto.getActive());

        return user;
    }
}
