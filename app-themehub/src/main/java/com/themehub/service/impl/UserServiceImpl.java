package com.themehub.service.impl;

import com.themehub.constant.ThemehubConstant;
import com.themehub.dto.UserDTO;
import com.themehub.dto.request.UserDTORequest;
import com.themehub.errorhandler.EntityUnprocessableException;
import com.themehub.mapper.UserMapper;
import com.themehub.model.User;
import com.themehub.repository.UserInterface;
import com.themehub.service.KeycloakService;
import com.themehub.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    final UserInterface userInterface;
    final KeycloakService keycloakService; // Inject KeycloakService

    final UserMapper userMapper;

    public UserServiceImpl(UserInterface userInterface, KeycloakService keycloakService, UserMapper userMapper) {
        this.userInterface = userInterface;
        this.keycloakService = keycloakService;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> findByKeyWordSQL(String key_word, String state) {
        List<User> list = this.userInterface.findByKeyWordSQL(key_word, state);
        return list.stream()
                .map((bean)-> userMapper.toDTO(bean))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserDTO saveSQL(UserDTORequest dto) throws EntityUnprocessableException {
        if (userInterface.existsByEmail(dto.getEmail())) {
            throw new EntityUnprocessableException();
        }
        User user = this.userInterface.saveSQL(dto.getUsername(), dto.getEmail(),
                                               dto.getPassword(), dto.getFirst_name(),
                                               dto.getLast_name(), dto.getCompany(),
                                               dto.getState());
        // Create the user in Keycloak
        keycloakService.createKeycloakUser(dto)
                .subscribe(
                        success -> System.out.println("User created successfully in Keycloak"),
                        error -> System.out.println("Error: " + error.getMessage())
                );

        // Return the user data as a DTO
        return convertBeanDto(user);
    }


    public Mono<String> loginUser(String username, String password) {
        return keycloakService.getRefreshToken(username, password)
                .doOnNext(refreshToken -> System.out.println("Fetched refresh token: " + refreshToken));
    }

    public Mono<String> logoutUser(String username, String password) {
        return keycloakService.getRefreshToken(username, password)
                .flatMap(refreshToken -> keycloakService.logout(refreshToken));
    }


    public UserDTO convertBeanDto ( User user){
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .first_name(user.getFirstName())
                .last_name(user.getLastName())
                .company(user.getCompany())
                .state(user.getState())
                .build();
    }


}
