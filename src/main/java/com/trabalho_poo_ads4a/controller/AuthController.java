package com.trabalho_poo_ads4a.controller;

import com.trabalho_poo_ads4a.model.dto.request.LoginDTO;
import com.trabalho_poo_ads4a.model.dto.request.SignupDTO;
import com.trabalho_poo_ads4a.model.dto.response.TokenResponseDTO;
import com.trabalho_poo_ads4a.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<TokenResponseDTO> signup(@RequestBody SignupDTO signupDTO) {
        return new ResponseEntity<>(authService.signup(signupDTO), HttpStatus.CREATED);
    }

}
