package com.compassuol.sp.challenge.msuser.controllers;

import com.compassuol.sp.challenge.msuser.domain.dto.UserRequest;
import com.compassuol.sp.challenge.msuser.domain.dto.UserResponse;
import com.compassuol.sp.challenge.msuser.domain.entities.User;
import com.compassuol.sp.challenge.msuser.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(userRequest));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUserById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserRequest userRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateUser(id, userRequest));
    }

}
