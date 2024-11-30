package com.trabalho_poo_ads4a.controller;

import com.trabalho_poo_ads4a.config.security.SecurityConfig;
import com.trabalho_poo_ads4a.model.dto.response.UserResponseDTO;
import com.trabalho_poo_ads4a.model.entity.User;
import com.trabalho_poo_ads4a.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable UUID idUser) {
        return ResponseEntity.ok(userService.findById(idUser));
    }

}
