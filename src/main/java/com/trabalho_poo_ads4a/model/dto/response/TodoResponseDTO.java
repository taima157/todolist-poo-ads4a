package com.trabalho_poo_ads4a.model.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class TodoResponseDTO {

    private UUID id;
    private String title;
    private String description;
    private Boolean completed;
    private UserResponseDTO user;

}
