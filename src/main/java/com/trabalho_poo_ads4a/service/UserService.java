package com.trabalho_poo_ads4a.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabalho_poo_ads4a.exceptions.NotFoundException;
import com.trabalho_poo_ads4a.model.dto.response.UserResponseDTO;
import com.trabalho_poo_ads4a.model.entity.User;
import com.trabalho_poo_ads4a.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    public UserResponseDTO findById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return convertEntityToDTO(user);
    }

    public List<UserResponseDTO> findAll() {
        return convertEntityListToDTOList(userRepository.findAll());
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    private UserResponseDTO convertEntityToDTO(User user) {
        return objectMapper.convertValue(user, UserResponseDTO.class);
    }

    private List<UserResponseDTO> convertEntityListToDTOList(List<User> userList) {
        return userList.stream().map(this::convertEntityToDTO).toList();
    }

}
