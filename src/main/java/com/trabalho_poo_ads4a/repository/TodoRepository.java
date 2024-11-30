package com.trabalho_poo_ads4a.repository;

import com.trabalho_poo_ads4a.model.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {

    List<Todo> findByUserId(UUID id);

}
