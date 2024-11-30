package com.trabalho_poo_ads4a.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabalho_poo_ads4a.exceptions.NotFoundException;
import com.trabalho_poo_ads4a.model.dto.request.TodoRequestDTO;
import com.trabalho_poo_ads4a.model.dto.response.TodoResponseDTO;
import com.trabalho_poo_ads4a.model.entity.Todo;
import com.trabalho_poo_ads4a.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final ObjectMapper objectMapper;

    TodoService(TodoRepository todoRepository, ObjectMapper objectMapper) {
        this.todoRepository = todoRepository;
        this.objectMapper = objectMapper;
    }

    public TodoResponseDTO findById(UUID id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new NotFoundException("Todo not found"));
        return convertEntityToDTO(todo);
    }

    public List<TodoResponseDTO> findAll() {
        return convertEntityListToDTOList(todoRepository.findAll());
    }

    public List<TodoResponseDTO> findAllByUserId(UUID idUser) {
        return convertEntityListToDTOList(todoRepository.findByUserId(idUser));
    }

    public TodoResponseDTO create(TodoRequestDTO todoRequestDTO) {
        Todo todo = convertDTOToEntity(todoRequestDTO);
        return convertEntityToDTO(todoRepository.save(todo));
    }

    public TodoResponseDTO update(UUID idTodo, TodoRequestDTO todoRequestDTO) {
        Todo todo = todoRepository.findById(idTodo).orElseThrow(() -> new NotFoundException("Todo not found"));

        todo.setTitle(todoRequestDTO.getTitle());
        todo.setDescription(todo.getDescription());
        todo.setCompleted(todoRequestDTO.getCompleted());

        return convertEntityToDTO(todoRepository.save(todo));
    }

    public void delete(UUID id) {
        todoRepository.deleteById(id);
    }

    private Todo convertDTOToEntity(TodoRequestDTO todoRequestDTO) {
        return objectMapper.convertValue(todoRequestDTO, Todo.class);
    }

    private TodoResponseDTO convertEntityToDTO(Todo todo) {
        return objectMapper.convertValue(todo, TodoResponseDTO.class);
    }

    private List<TodoResponseDTO> convertEntityListToDTOList(List<Todo> todos) {
        return todos.stream().map(this::convertEntityToDTO).toList();
    }

}
