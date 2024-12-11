package com.trabalho_poo_ads4a.service;

import com.trabalho_poo_ads4a.exceptions.NotFoundException;
import com.trabalho_poo_ads4a.exceptions.UnauthorizedException;
import com.trabalho_poo_ads4a.model.dto.request.LoginDTO;
import com.trabalho_poo_ads4a.model.dto.request.SignupDTO;
import com.trabalho_poo_ads4a.model.dto.request.UserRequestDTO;
import com.trabalho_poo_ads4a.model.dto.response.TokenResponseDTO;
import com.trabalho_poo_ads4a.model.entity.User;
import com.trabalho_poo_ads4a.service.security.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

class AuthServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthService authService;

    private LoginDTO loginDTO;
    private SignupDTO signupDTO;
    private User user;
    private TokenResponseDTO tokenResponseDTO;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        setupVariables();
    }

    @Test
    void loginWithSuccess() {
        Mockito.when(userService.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())).thenReturn(Boolean.TRUE);
        Mockito.when(tokenService.generateToken(user)).thenReturn(Mockito.anyString());

        TokenResponseDTO result = authService.login(loginDTO);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getToken());
    }

    @Test
    void loginThrowsException() {
        Mockito.when(userService.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())).thenReturn(Boolean.FALSE);

        Assertions.assertThrows(UnauthorizedException.class, () -> authService.login(loginDTO));
    }

    @Test
    void signupWithSuccess() {
        SignupDTO request = new SignupDTO();
        request.setEmail("email@email.com");
        request.setPassword("password");
        request.setName("Test name");

        Mockito.when(userService.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode(request.getPassword())).thenReturn(Mockito.anyString());
        Mockito.when(userService.save(user)).thenReturn(user);
        Mockito.when(tokenService.generateToken(user)).thenReturn(Mockito.anyString());

        TokenResponseDTO result = authService.signup(request);

        Assertions.assertNotNull(result);
    }

    void setupVariables() {
        loginDTO = new LoginDTO();
        loginDTO.setEmail("email@email.com");
        loginDTO.setPassword("password");

        signupDTO = new SignupDTO();
        signupDTO.setName("Name test");
        signupDTO.setEmail("email@email.com");
        signupDTO.setPassword("password");

        user = new User();
        user.setEmail("email@email.com");
        user.setName("Name test");

        String encodedPassword = passwordEncoder.encode(loginDTO.getPassword());
        user.setPassword(encodedPassword);

    }
}