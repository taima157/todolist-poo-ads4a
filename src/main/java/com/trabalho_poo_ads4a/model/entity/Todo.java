package com.trabalho_poo_ads4a.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "todo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;
    private Boolean completed;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}
