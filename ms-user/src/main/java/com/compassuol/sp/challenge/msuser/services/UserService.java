package com.compassuol.sp.challenge.msuser.services;

import com.compassuol.sp.challenge.msuser.domain.dto.Notification;
import com.compassuol.sp.challenge.msuser.domain.dto.UserRequest;
import com.compassuol.sp.challenge.msuser.domain.dto.UserResponse;
import com.compassuol.sp.challenge.msuser.domain.entities.User;
import com.compassuol.sp.challenge.msuser.exceptions.NotFound;
import com.compassuol.sp.challenge.msuser.mapper.UserMapper;
import com.compassuol.sp.challenge.msuser.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final PublisherService pub;

    public UserService(UserRepository userRepository, PublisherService publisherService) {
        repository = userRepository;
        pub = publisherService;
    }

    public UserResponse createUser(UserRequest userRequest) {

        User saved = repository.save(UserMapper.toUser(userRequest));

        pub.send(userRequest.getEmail(), "CREATE");

        return UserMapper.toDto(saved);
    }

    public UserResponse getUserById(Long id) throws NotFound {
        return UserMapper.toDto(repository.findById(id)
                .orElseThrow(() -> new NotFound("User not found")));
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) throws NotFound {
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFound("User not found"));

        pub.send(String.format("OLD: %s, NEW: %s",
                        user.getEmail(),
                        userRequest.getEmail()),
                "UPDATE");

        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setCpf(userRequest.getCpf());
        user.setEmail(userRequest.getEmail());
        user.setActive(userRequest.getActive());
        user.setBirthdate(userRequest.getBirthdate());

        return UserMapper.toDto(repository.save(user));
    }

}
