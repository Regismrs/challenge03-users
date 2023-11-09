package com.compassuol.sp.challenge.msuser.controllers;

import com.compassuol.sp.challenge.msuser.domain.dtos.request.UpdateUserDto;
import com.compassuol.sp.challenge.msuser.domain.dtos.request.UpdateUserPasswordDto;
import com.compassuol.sp.challenge.msuser.domain.dtos.response.UserResponse;
import com.compassuol.sp.challenge.msuser.domain.entities.User;
import com.compassuol.sp.challenge.msuser.domain.dtos.request.LoginRequest;
import com.compassuol.sp.challenge.msuser.domain.dtos.request.CreateUserDto;
import com.compassuol.sp.challenge.msuser.domain.dtos.response.LoginResponse;
import com.compassuol.sp.challenge.msuser.services.UserService;
import com.compassuol.sp.challenge.msuser.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(
            @RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.login(loginRequest));
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> register(
            @RequestBody @Valid CreateUserDto createUserDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(createUserDto));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUserById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UpdateUserDto updateDto) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateUser(id, updateDto));
    }

    @PutMapping("/users/{id}/password")
    public ResponseEntity<UserResponse> updateUserPassword(
            @PathVariable Long id,
            @RequestBody @Valid UpdateUserPasswordDto updatePasswordDto) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateUserPassword(id, updatePasswordDto));
    }
}
