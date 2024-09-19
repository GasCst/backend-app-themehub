package com.themehub.service;

import com.themehub.dto.request.UserDTORequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public interface KeycloakService {
    Mono<Map<String, String>> fetchTokenMap(String username, String password);
    Mono<String> getRefreshToken(String username, String password);
    Mono<String> getToken(String username, String password);
    Mono<String> logout(String refreshToken);
    Mono<Void> createKeycloakUser(UserDTORequest dto);
}
