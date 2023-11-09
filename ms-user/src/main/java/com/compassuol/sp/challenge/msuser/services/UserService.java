package com.compassuol.sp.challenge.msuser.services;

import com.compassuol.sp.challenge.msuser.domain.dtos.request.LoginRequest;
import com.compassuol.sp.challenge.msuser.domain.dtos.request.CreateUserDto;
import com.compassuol.sp.challenge.msuser.domain.dtos.request.UpdateUserDto;
import com.compassuol.sp.challenge.msuser.domain.dtos.request.UpdateUserPasswordDto;
import com.compassuol.sp.challenge.msuser.domain.dtos.response.LoginResponse;
import com.compassuol.sp.challenge.msuser.domain.dtos.response.UserResponse;
import com.compassuol.sp.challenge.msuser.domain.entities.User;
import com.compassuol.sp.challenge.msuser.exceptions.NotFound;
import com.compassuol.sp.challenge.msuser.mapper.UserMapper;
import com.compassuol.sp.challenge.msuser.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//AuthenticationService
@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final  JwtService jwtService;

    private final PublisherService mqService;

    public UserService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            PublisherService mqService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.mqService = mqService;
    }

    public UserResponse getUserById(Long id) {
        return UserMapper.toDto(findUserById(id));
    }

    public UserResponse updateUser(Long id, UpdateUserDto updateDto) {
        User user = UserMapper.applyDtoToUser(updateDto, findUserById(id));

        return UserMapper.toDto(userRepository.save(user));
    }

    public UserResponse updateUserPassword(Long id, UpdateUserPasswordDto updateDto) {
        User user = findUserById(id);
        user.setPassword(
                passwordEncoder.encode(updateDto.getPassword()));

        return UserMapper.toDto(userRepository.save(user));
    }

    public UserResponse createUser(CreateUserDto request) {
        User user = UserMapper.toUser(request);
        user.setPassword(
                passwordEncoder.encode(request.getPassword()));

        User saved = userRepository.save(user);

        mqService.send(saved.getEmail(), "CREATE");

        return UserMapper.toDto(saved);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User authenticatedUser = authenticate(loginRequest);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        System.out.println(jwtToken);

        return LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();
    }

    private User authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        return userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid Username"));
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFound(String.format("User %d not found.", id)));
    }

}
