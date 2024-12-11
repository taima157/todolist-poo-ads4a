package com.trabalho_poo_ads4a.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabalho_poo_ads4a.exceptions.NotFoundException;
import com.trabalho_poo_ads4a.model.dto.request.TodoRequestDTO;
import com.trabalho_poo_ads4a.model.dto.request.UserRequestDTO;
import com.trabalho_poo_ads4a.model.dto.response.TodoResponseDTO;
import com.trabalho_poo_ads4a.model.dto.response.UserResponseDTO;
import com.trabalho_poo_ads4a.model.entity.Todo;
import com.trabalho_poo_ads4a.model.entity.User;
import com.trabalho_poo_ads4a.repository.TodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private TodoService todoService;

    private Todo todo;
    private TodoResponseDTO todoResponseDTO;
    private User user;
    private UserResponseDTO userResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        setupVariables();
    }

    @Test
    void findByIdWithSuccess() {
        UUID id = UUID.randomUUID();
        todo.setId(id);
        todoResponseDTO.setId(id);

        Mockito.when(todoRepository.findById(id)).thenReturn(Optional.ofNullable(todo));
        Mockito.when(objectMapper.convertValue(todo, TodoResponseDTO.class)).thenReturn(todoResponseDTO);

        TodoResponseDTO result = todoService.findById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(id, result.getId());
    }

    @Test
    void findByIdThrowsException() {
        Assertions.assertThrows(NotFoundException.class, () -> todoService.findById(Mockito.any()));
    }

    @Test
    void findAllWithSuccess() {
        Mockito.when(todoRepository.findAll()).thenReturn(List.of(todo));
        Mockito.when(objectMapper.convertValue(todo, TodoResponseDTO.class)).thenReturn(todoResponseDTO);

        List<TodoResponseDTO> result = todoService.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), 1);
    }

    @Test
    void findAllByUserIdWithSuccess() {
        UUID id = UUID.randomUUID();
        todoResponseDTO.getUser().setId(id);

        Mockito.when(todoRepository.findByUserId(id)).thenReturn(List.of(todo));
        Mockito.when(objectMapper.convertValue(todo, TodoResponseDTO.class)).thenReturn(todoResponseDTO);

        List<TodoResponseDTO> result = todoService.findAllByUserId(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), 1);
        Assertions.assertEquals(id, result.stream().findFirst().get().getUser().getId());
    }

    @Test
    void createWithSuccess() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setId(UUID.randomUUID());
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO();
        todoRequestDTO.setUser(userRequestDTO);

        Mockito.when(objectMapper.convertValue(todoRequestDTO, Todo.class)).thenReturn(todo);
        Mockito.when(todoRepository.save(todo)).thenReturn(todo);
        Mockito.when(objectMapper.convertValue(todo, TodoResponseDTO.class)).thenReturn(todoResponseDTO);

        TodoResponseDTO result = todoService.create(todoRequestDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getTitle(), "Test title");
    }

    @Test
    void updateWithSuccess() {
        UUID id = UUID.randomUUID();
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO();
        todoRequestDTO.setTitle("Title test 2");

        todoResponseDTO.setTitle("Title test 2");
        todoResponseDTO.setId(id);

        Mockito.when(todoRepository.findById(id)).thenReturn(Optional.of(todo));
        Mockito.when(todoRepository.save(Mockito.any())).thenReturn(todo);
        Mockito.when(objectMapper.convertValue(todo, TodoResponseDTO.class)).thenReturn(todoResponseDTO);

        TodoResponseDTO result = todoService.update(id, todoRequestDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), id);
        Assertions.assertEquals(result.getTitle(), "Title test 2");
    }

    @Test
    void deleteWithSuccess() {
        UUID id = UUID.randomUUID();
        todoService.delete(id);
        Mockito.verify(todoRepository, Mockito.times(1)).deleteById(id);

    }

    void setupVariables() {
        todo = new Todo();
        todo.setId(UUID.randomUUID());
        todo.setCompleted(Boolean.FALSE);
        todo.setTitle("Test title");
        todo.setDescription("Test description");

        user = new User();
        user.setId(UUID.randomUUID());

        todo.setUser(user);

        todoResponseDTO = new TodoResponseDTO();
        todoResponseDTO.setId(UUID.randomUUID());
        todoResponseDTO.setCompleted(Boolean.FALSE);
        todoResponseDTO.setTitle("Test title");
        todoResponseDTO.setDescription("Test description");

        userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(UUID.randomUUID());

        todoResponseDTO.setUser(userResponseDTO);
    }
}