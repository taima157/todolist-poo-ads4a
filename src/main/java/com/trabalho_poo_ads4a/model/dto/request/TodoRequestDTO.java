package com.trabalho_poo_ads4a.model.dto.request;

import lombok.Data;

@Data
public class TodoRequestDTO {

    private String title;
    private String description;
    private Boolean completed;
    private UserRequestDTO user;

}
