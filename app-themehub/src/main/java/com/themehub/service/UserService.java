package com.themehub.service;

import com.themehub.dto.UserDTO;
import com.themehub.dto.request.UserDTORequest;
import com.themehub.model.User;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserService {
    List<UserDTO> findByKeyWordSQL (String key_word, String state);
    UserDTO saveSQL (UserDTORequest dto);
    Mono<String> logoutUser(String username, String password);
    Mono<String> loginUser(String username, String password);
    User getUserByUsername(String username);
}






