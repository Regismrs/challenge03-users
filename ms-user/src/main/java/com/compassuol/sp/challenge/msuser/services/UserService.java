package com.compassuol.sp.challenge.msuser.services;

import com.compassuol.sp.challenge.msuser.domain.dto.UserRequest;
import com.compassuol.sp.challenge.msuser.domain.dto.UserResponse;
import com.compassuol.sp.challenge.msuser.domain.entities.User;
import com.compassuol.sp.challenge.msuser.exceptions.NotFound;
import com.compassuol.sp.challenge.msuser.mapper.UserMapper;
import com.compassuol.sp.challenge.msuser.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        repository = userRepository;
    }

    public UserResponse createUser(UserRequest userRequest) {
        return UserMapper.toDto(
                repository.save(
                        UserMapper.toUser(userRequest)));
    }

    public UserResponse getUserById(Long id) throws NotFound {
        return UserMapper.toDto(repository.findById(id)
                .orElseThrow(() -> new NotFound("User not found")));
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) throws NotFound {
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFound("User not found"));

        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setCpf(userRequest.getCpf());
        user.setEmail(userRequest.getEmail());
        user.setActive(userRequest.getActive());
        user.setBirthdate(userRequest.getBirthdate());

        return UserMapper.toDto(repository.save(user));
    }

}
