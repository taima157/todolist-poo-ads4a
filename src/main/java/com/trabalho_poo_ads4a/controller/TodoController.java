package com.trabalho_poo_ads4a.controller;

import com.trabalho_poo_ads4a.config.security.SecurityConfig;
import com.trabalho_poo_ads4a.model.dto.request.TodoRequestDTO;
import com.trabalho_poo_ads4a.model.dto.response.TodoResponseDTO;
import com.trabalho_poo_ads4a.model.entity.Todo;
import com.trabalho_poo_ads4a.service.TodoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todo")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class TodoController {

    private final TodoService todoService;

    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/{idTodo}")
    public ResponseEntity<TodoResponseDTO> getById(@PathVariable UUID idTodo) {
        return ResponseEntity.ok(todoService.findById(idTodo));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> getAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@RequestBody TodoRequestDTO todoRequestDTO) {
        TodoResponseDTO todo = todoService.create(todoRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(todo.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{idTodo}")
    public ResponseEntity<TodoResponseDTO> update(@PathVariable UUID idTodo, @RequestBody TodoRequestDTO todoRequestDTO) {
        return new ResponseEntity<>(todoService.update(idTodo, todoRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<TodoResponseDTO>> getAllByUserId(@PathVariable UUID idUser) {
        return new ResponseEntity<>(todoService.findAllByUserId(idUser), HttpStatus.OK);
    }

    @DeleteMapping("/{idTodo}")
    public ResponseEntity<Void> delete(@PathVariable UUID idTodo) {
        todoService.delete(idTodo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
