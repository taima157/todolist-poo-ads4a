package com.trabalho_poo_ads4a.service;

import com.trabalho_poo_ads4a.model.dto.request.SignupDTO;
import com.trabalho_poo_ads4a.model.dto.response.TokenResponseDTO;
import com.trabalho_poo_ads4a.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserService userService;

    private SignupDTO signupDTO;
    private TokenResponseDTO tokenResponseDTO;

    @BeforeEach
    void setUp() {
        signupDTO = new SignupDTO();
        signupDTO.setEmail("email@email.com");
        signupDTO.setPassword("password");
        signupDTO.setName("Nome Teste");
    }

    @Test
    public void testCreateUser() {
        Mockito.when(authService.signup(signupDTO)).thenReturn(tokenResponseDTO);
        Mockito.when(userService.findByEmail(Mockito.anyString())).thenReturn(Optional.of(new User()));
        TokenResponseDTO result = authService.signup(signupDTO);

        assertNotNull(result.getToken());
    }

}
