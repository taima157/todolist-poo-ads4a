package com.trabalho_poo_ads4a.service;

import com.trabalho_poo_ads4a.exceptions.BadRequestException;
import com.trabalho_poo_ads4a.exceptions.UnauthorizedException;
import com.trabalho_poo_ads4a.model.dto.request.LoginDTO;
import com.trabalho_poo_ads4a.model.dto.request.SignupDTO;
import com.trabalho_poo_ads4a.model.dto.response.TokenResponseDTO;
import com.trabalho_poo_ads4a.model.entity.User;
import com.trabalho_poo_ads4a.service.security.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public TokenResponseDTO login(LoginDTO loginDTO) {
        Optional<User> user = userService.findByEmail(loginDTO.getEmail());

        if (user.isPresent() && passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            return new TokenResponseDTO(tokenService.generateToken(user.get()));
        }

        throw new UnauthorizedException("Invalid email or password");
    }


    public TokenResponseDTO signup(SignupDTO signupDTO) {
        Optional<User> userDB = userService.findByEmail(signupDTO.getEmail());

        if (userDB.isPresent()) {
            throw new BadRequestException("Email already in use");
        }

        User newUser = new User();
        newUser.setName(signupDTO.getName());
        newUser.setEmail(signupDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(signupDTO.getPassword()));

        userService.save(newUser);

        return new TokenResponseDTO(tokenService.generateToken(newUser));
    }

}
