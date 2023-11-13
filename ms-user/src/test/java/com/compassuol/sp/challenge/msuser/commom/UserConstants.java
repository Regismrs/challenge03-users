package com.compassuol.sp.challenge.msuser.commom;

import com.compassuol.sp.challenge.msuser.domain.dtos.request.CreateUserDto;
import com.compassuol.sp.challenge.msuser.domain.dtos.request.LoginRequest;
import com.compassuol.sp.challenge.msuser.domain.dtos.request.UpdateUserDto;
import com.compassuol.sp.challenge.msuser.domain.dtos.request.UpdateUserPasswordDto;
import com.compassuol.sp.challenge.msuser.domain.dtos.response.LoginResponse;
import com.compassuol.sp.challenge.msuser.domain.dtos.response.UserResponse;
import com.compassuol.sp.challenge.msuser.domain.entities.User;

import java.util.Date;

public class UserConstants {

    public static final User INVALID_USER = new User();
    private static final Date DATE = new Date(0L);
    public static final User VALID_USER = new User(1L, "abc", "def",
            "000.000.000-00",DATE,"a@b.c",
            "$2a$10$gq7D1KBEw5fqB3wUYV9bd.JsMEioT48oW0qZGaW73e9z2NMXLbbSu",
            true, DATE,DATE);

    public static final UserResponse VALID_USER_RESPONSE = new UserResponse(1L, "abc",
            "def", "000.000.000-00", "a@b.c", true, DATE, DATE, DATE);

    public static final CreateUserDto VALID_CREATE_USER_DTO = new CreateUserDto("abc",
            "def", "000.000.000-00", DATE, "a@b.c", "123456", true);

    public static final CreateUserDto INVALID_CREATE_USER_DTO = new CreateUserDto("",
            "", "000.000.000-00", DATE, "a@b.c", "", true);

    public static final UpdateUserDto VALID_UPDATE_USER_DTO = new UpdateUserDto("abc",
            "def", "000.000.000-00", DATE, "a@b.c", true);

    public static final UpdateUserDto INVALID_UPDATE_USER_DTO = new UpdateUserDto("",
            "", "", DATE, "", true);

    public static final UpdateUserPasswordDto VALID_UPDATE_USER_PASSWORD_DTO =
            new UpdateUserPasswordDto("123456");

    public static final UpdateUserPasswordDto INVALID_UPDATE_USER_PASSWORD_DTO =
            new UpdateUserPasswordDto("");

    public static final LoginRequest VALID_LOGIN_REQUEST = new LoginRequest("a@b.c", "123456");

    public static final LoginRequest INVALID_LOGIN_REQUEST = new LoginRequest("", "");

}
