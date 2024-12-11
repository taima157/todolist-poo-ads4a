package com.trabalho_poo_ads4a.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabalho_poo_ads4a.exceptions.NotFoundException;
import com.trabalho_poo_ads4a.model.dto.response.UserResponseDTO;
import com.trabalho_poo_ads4a.model.entity.User;
import com.trabalho_poo_ads4a.repository.UserRepository;
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

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByIdWithSuccess() {
        UUID id = UUID.randomUUID();
        User userDb = setupUser(id);
        UserResponseDTO response = setupUserResposeDTO(id);

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(userDb));
        Mockito.when(objectMapper.convertValue(userDb, UserResponseDTO.class)).thenReturn(response);

        UserResponseDTO userResponseDTO = userService.findById(id);

        Assertions.assertNotNull(userResponseDTO);
        Assertions.assertEquals(userResponseDTO.getId(), id);
    }

    @Test
    void findByIdThrowsException() {
        Assertions.assertThrows(NotFoundException.class, () -> userService.findById(Mockito.any()));
    }

    @Test
    void findAllWithSuccess() {
        UUID id = UUID.randomUUID();
        User userDb = setupUser(id);
        UserResponseDTO response = setupUserResposeDTO(id);

        Mockito.when(userRepository.findAll()).thenReturn(List.of(userDb));
        Mockito.when(objectMapper.convertValue(userDb, UserResponseDTO.class)).thenReturn(response);

        List<UserResponseDTO> userResponseDTOs = userService.findAll();

        Assertions.assertNotNull(userResponseDTOs);
        Assertions.assertEquals(userResponseDTOs.size(), 1);
    }

    @Test
    void findByEmail() {
        UUID id = UUID.randomUUID();
        User userDb = setupUser(id);
        UserResponseDTO response = setupUserResposeDTO(id);

        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(userDb));
        Mockito.when(objectMapper.convertValue(userDb, UserResponseDTO.class)).thenReturn(response);

        Optional<User> userResponseDTO = userService.findByEmail(Mockito.anyString());

        Assertions.assertNotNull(userResponseDTO);
        Assertions.assertEquals(userResponseDTO.get().getEmail(), "joao@gmail.com");
    }

    private User setupUser(UUID id) {
        User user = new User();
        user.setName("João");
        user.setEmail("joao@gmail.com");
        user.setId(id);

        return user;
    }

    private UserResponseDTO setupUserResposeDTO(UUID id) {
        UserResponseDTO user = new UserResponseDTO();
        user.setName("João");
        user.setEmail("joao@gmail.com");
        user.setId(id);

        return user;
    }
}