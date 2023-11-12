package com.compassuol.sp.challenge.msuser.services;

import com.compassuol.sp.challenge.msuser.domain.dtos.response.LoginResponse;
import com.compassuol.sp.challenge.msuser.domain.dtos.response.UserResponse;
import com.compassuol.sp.challenge.msuser.domain.entities.User;
import com.compassuol.sp.challenge.msuser.exceptions.NotFound;
import com.compassuol.sp.challenge.msuser.mapper.UserMapper;
import com.compassuol.sp.challenge.msuser.repositories.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static com.compassuol.sp.challenge.msuser.commom.UserConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService service;
    @Mock
    private UserRepository repository;
    @Mock
    private PublisherService mqService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void getUserById_WithExistentId_ReturnsUserResponse() {
        when(repository.findById(1L)).thenReturn(Optional.of(VALID_USER));

        UserResponse sut = service.getUserById(1L);

        assertThat(sut).isNotNull().isInstanceOf(UserResponse.class);
        assertThat(sut.getId()).isEqualTo(VALID_USER.getId());
        assertThat(sut.getActive()).isEqualTo(VALID_USER.getActive());
        assertThat(sut.getBirthdate()).isEqualTo(VALID_USER.getBirthdate());
        assertThat(sut.getCreated_at()).isEqualTo(VALID_USER.getCreatedAt());
        assertThat(sut.getCpf()).isEqualTo(VALID_USER.getCpf());
        assertThat(sut.getEmail()).isEqualTo(VALID_USER.getEmail());
        assertThat(sut.getFirstName()).isEqualTo(VALID_USER.getFirstName());
        assertThat(sut.getLastName()).isEqualTo(VALID_USER.getLastName());
        assertThat(sut.getUpdated_at()).isEqualTo(VALID_USER.getUpdatedAt());
    }

    @Test
    void getUserById_WithNonExistentId_throwsNotFound() {
        // Not found
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getUserById(1L))
                .isInstanceOf(NotFound.class);
    }

    @Test
    void updateUser_WIthExistentIdAndValidData_ReturnsUserResponse() {
        when(repository.findById(1L)).thenReturn(Optional.of(VALID_USER));
        when(repository.save(any(User.class))).thenReturn(VALID_USER);

        UserResponse sut = service.updateUser(1L, VALID_UPDATE_USER_DTO);

        assertThat(sut).isNotNull().isInstanceOf(UserResponse.class);
        assertThat(sut.getId()).isEqualTo(VALID_USER.getId());
        assertThat(sut.getActive()).isEqualTo(VALID_USER.getActive());
        assertThat(sut.getBirthdate()).isEqualTo(VALID_USER.getBirthdate());
        assertThat(sut.getCreated_at()).isEqualTo(VALID_USER.getCreatedAt());
        assertThat(sut.getCpf()).isEqualTo(VALID_USER.getCpf());
        assertThat(sut.getEmail()).isEqualTo(VALID_USER.getEmail());
        assertThat(sut.getFirstName()).isEqualTo(VALID_USER.getFirstName());
        assertThat(sut.getLastName()).isEqualTo(VALID_USER.getLastName());
        assertThat(sut.getUpdated_at()).isEqualTo(VALID_USER.getUpdatedAt());
    }

    @Test
    void updateUser_WithNonExistentId_throwsNotFound() {
        // Not found
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.updateUser(1L, VALID_UPDATE_USER_DTO))
                .isInstanceOf(NotFound.class);
    }

    @Test
    void updateUser_WithExistentIdAndDuplicatedEmail_throwsException() {
        // Duplicate Unique keys (email, cpf)
        when(repository.findById(1L)).thenReturn(Optional.of(VALID_USER));
        when(repository.save(any(User.class))).thenThrow(DataIntegrityViolationException.class);

        assertThatThrownBy(() -> service.updateUser(1L, INVALID_UPDATE_USER_DTO))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void updateUserPassword_WithExistentIdAndValidData_ReturnsUserResponse() {
        when(repository.findById(1L)).thenReturn(Optional.of(VALID_USER));
        when(repository.save(any(User.class))).thenReturn(VALID_USER);

        UserResponse sut = service.updateUserPassword(1L, VALID_UPDATE_USER_PASSWORD_DTO);

        assertThat(sut).isNotNull().isInstanceOf(UserResponse.class);
        assertThat(sut.getId()).isEqualTo(VALID_USER.getId());
        assertThat(sut.getActive()).isEqualTo(VALID_USER.getActive());
        assertThat(sut.getBirthdate()).isEqualTo(VALID_USER.getBirthdate());
        assertThat(sut.getCreated_at()).isEqualTo(VALID_USER.getCreatedAt());
        assertThat(sut.getCpf()).isEqualTo(VALID_USER.getCpf());
        assertThat(sut.getEmail()).isEqualTo(VALID_USER.getEmail());
        assertThat(sut.getFirstName()).isEqualTo(VALID_USER.getFirstName());
        assertThat(sut.getLastName()).isEqualTo(VALID_USER.getLastName());
        assertThat(sut.getUpdated_at()).isEqualTo(VALID_USER.getUpdatedAt());
    }

    @Test
    void updateUserPassword_WithNonExistentId_throwsNotFound() {
        // Not Found
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.updateUserPassword(1L, VALID_UPDATE_USER_PASSWORD_DTO))
                .isInstanceOf(NotFound.class);
    }

    @Test
    void createUser_WithValidData_ReturnsUserResponse() {
        when(repository.save(any(User.class))).thenReturn(VALID_USER);

        UserResponse sut = service.createUser(VALID_CREATE_USER_DTO);

        assertThat(sut).isNotNull().isInstanceOf(UserResponse.class);
        assertThat(sut.getId()).isEqualTo(VALID_USER.getId());
        assertThat(sut.getActive()).isEqualTo(VALID_USER.getActive());
        assertThat(sut.getBirthdate()).isEqualTo(VALID_USER.getBirthdate());
        assertThat(sut.getCreated_at()).isEqualTo(VALID_USER.getCreatedAt());
        assertThat(sut.getCpf()).isEqualTo(VALID_USER.getCpf());
        assertThat(sut.getEmail()).isEqualTo(VALID_USER.getEmail());
        assertThat(sut.getFirstName()).isEqualTo(VALID_USER.getFirstName());
        assertThat(sut.getLastName()).isEqualTo(VALID_USER.getLastName());
        assertThat(sut.getUpdated_at()).isEqualTo(VALID_USER.getUpdatedAt());
    }

    @Test
    void createUser_WithDuplicatedEmail_throwsException() {
        // Duplicate Unique keys (email, cpf)
        when(repository.save(any(User.class))).thenThrow(DataIntegrityViolationException.class);

        assertThatThrownBy(() -> service.createUser(INVALID_CREATE_USER_DTO))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void login_WithExistentUser_ReturnsLoginResponse() {
        String TOKEN = "token";
        Long EXPIRATION_TIME = 1000L;

        when(jwtService.generateToken(any())).thenReturn(TOKEN);
        when(jwtService.getExpirationTime()).thenReturn(EXPIRATION_TIME);
        when(repository.findByEmail(any(String.class))).thenReturn(Optional.of(VALID_USER));

        LoginResponse sut = service.login(VALID_LOGIN_REQUEST);

        assertThat(sut).isNotNull().isInstanceOf(LoginResponse.class);
        assertThat(sut.getToken()).isEqualTo(TOKEN);
        assertThat(sut.getExpiresIn()).isEqualTo(EXPIRATION_TIME);
    }
}