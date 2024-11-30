package com.trabalho_poo_ads4a.model.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private String password;

}
