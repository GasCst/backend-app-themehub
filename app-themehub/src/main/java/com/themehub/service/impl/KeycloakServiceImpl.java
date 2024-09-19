package com.themehub.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.themehub.dto.request.UserDTORequest;
import com.themehub.service.KeycloakService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class KeycloakServiceImpl implements KeycloakService {

    private final WebClient webClient;

    // Un campo per conservare il refresh token
    private String savedRefreshToken;
    private String Token;

    public KeycloakServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/realms/myrealm/protocol/openid-connect")
                .build();
    }


    public Mono<Map<String, String>> fetchTokenMap(String username, String password) {
        String url = "http://localhost:8080/realms/myrealm/protocol/openid-connect/token";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("client_id", "myclient");
        formData.add("client_secret", "7pu3BPtDDQaW3KWqhwcJMsRtoaoDfuiq");
        formData.add("username", username);
        formData.add("password", password);

        return webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {});
    }

    @Override
    public Mono<String> getRefreshToken(String username, String password) {
        if (savedRefreshToken != null) {
            // Se il token è già stato salvato, lo utilizza
            return Mono.just(savedRefreshToken);
        }

        // Altrimenti, lo richiede e lo salva
        return fetchTokenMap(username, password)
                .map(response -> {
                    savedRefreshToken = response.get("refresh_token");
                    System.out.println("Saved Refresh Token: " + savedRefreshToken);
                    return savedRefreshToken;
                });
    }

    @Override
    public Mono<String> getToken(String username, String password) {
        return fetchTokenMap(username, password)
                .map(response -> {
                    Token = response.get("access_token");
                    savedRefreshToken = response.get("refresh_token");
                    System.out.println("Saved Token: " + Token);
                    return Token;
                });
    }



    @Override
    public Mono<String> logout(String refreshToken) {
        String url = "http://localhost:8080/realms/myrealm/protocol/openid-connect/logout";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", "myclient");
        formData.add("client_secret", "7pu3BPtDDQaW3KWqhwcJMsRtoaoDfuiq");
        formData.add("refresh_token", refreshToken);

        return webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> {
                    // Pulisce il token salvato dopo il logout
                    savedRefreshToken = null;
                    System.out.println("Logout completato e refresh token resettato.");
                });
    }

    @Override
    public Mono<Void> createKeycloakUser(UserDTORequest dto) {
        return getAdminAccessToken()
                .flatMap(token -> {
                    String url = "http://localhost:8080/admin/realms/myrealm/users";


                    // Construct the payload
                    Map<String, Object> keycloakUser = new HashMap<>();
                    keycloakUser.put("username", dto.getUsername());
                    keycloakUser.put("email", dto.getEmail());
                    keycloakUser.put("enabled", true);

                    if (dto.getFirst_name() != null) {
                        keycloakUser.put("firstName", dto.getFirst_name());
                    }
                    if (dto.getLast_name() != null) {
                        keycloakUser.put("lastName", dto.getLast_name());
                    }

                    Map<String, String> credentials = new HashMap<>();
                    credentials.put("type", "password");
                    credentials.put("value", dto.getPassword());
                    credentials.put("temporary", "false");

                    keycloakUser.put("credentials", Collections.singletonList(credentials));

                    System.out.println("Request Payload: " + keycloakUser);

                    return webClient.post()
                            .uri(url)
                            .header("Authorization", "Bearer " + token)
                            .bodyValue(keycloakUser)
                            .retrieve()
                            .onStatus(
                                    status -> status.isError(),
                                    response -> {
                                        return response.bodyToMono(String.class).flatMap(error -> {
                                            return Mono.error(new RuntimeException("Failed to create user in Keycloak: " + error));
                                        });
                                    }
                            )
                            .bodyToMono(Void.class)
                            .then(assignRoleToUser(token, dto.getUsername(), "customer"));
                });
    }

    private Mono<Void> assignRoleToUser(String token, String username, String roleName) {
        return findUserIdByUsername(token, username)
                .flatMap(userId -> findRoleIdByRoleName(token, roleName)
                        .flatMap(roleId -> {
                            String assignRoleUrl = "http://localhost:8080/admin/realms/myrealm/users/" + userId + "/role-mappings/realm";

                            Map<String, Object> role = new HashMap<>();
                            role.put("id", roleId);
                            role.put("name", roleName);

                            return webClient.post()
                                    .uri(assignRoleUrl)
                                    .header("Authorization", "Bearer " + token)
                                    .bodyValue(Collections.singletonList(role))
                                    .retrieve()
                                    .onStatus(
                                            status -> status.isError(),
                                            response -> response.bodyToMono(String.class)
                                                    .flatMap(error -> Mono.error(new RuntimeException("Failed to assign role in Keycloak: " + error)))
                                    )
                                    .bodyToMono(Void.class);
                        })
                );
    }

    private Mono<String> getAdminAccessToken() {
        String url = "http://localhost:8080/realms/myrealm/protocol/openid-connect/token";

        return webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue("grant_type=client_credentials&client_id=admin-cli&client_secret=CkcpoXA6o10HVElyyveXtWtbl6FzOIPq")
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (String) response.get("access_token"));
    }

    private Mono<String> findUserIdByUsername(String token, String username) {
        String url = "http://localhost:8080/admin/realms/myrealm/users?username=" + username;
        return webClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {})
                .map(users -> users.get(0).get("id").toString());
    }

    private Mono<String> findRoleIdByRoleName(String token, String roleName) {
        String url = "http://localhost:8080/admin/realms/myrealm/roles/" + roleName;
        return webClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(Map.class)
                .map(role -> role.get("id").toString());
    }



}
