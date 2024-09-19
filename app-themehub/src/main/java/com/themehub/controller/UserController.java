package com.themehub.controller;


import com.themehub.constant.ThemehubConstant;
import com.themehub.dto.UserDTO;
import com.themehub.dto.request.UserDTORequest;
import com.themehub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(ThemehubConstant.RESOURCE_GENERIC)
public class UserController {
    final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(ThemehubConstant.RESOURCE_USERS + ThemehubConstant.RESOURCE_USERS_USER)
    public ResponseEntity<List<UserDTO>> findByKeyWordSQL(@RequestParam String key_word, @RequestParam String state){
        return new ResponseEntity<List<UserDTO>>(this.userService.findByKeyWordSQL(key_word, state), HttpStatus.OK);
    }

    @PostMapping(ThemehubConstant.RESOURCE_USERS + ThemehubConstant.RESOURCE_USERS_USER)
    public ResponseEntity<UserDTO> saveSQL(@Valid @RequestBody UserDTORequest dto) {
        return new ResponseEntity<>(this.userService.saveSQL(dto), HttpStatus.CREATED);
    }


    @PostMapping(ThemehubConstant.RESOURCE_USERS + "/login")
    public Mono<ResponseEntity<String>> loginUser(@RequestParam String username, @RequestParam String password) {
        return userService.loginUser(username, password)
                .map(refreshToken -> new ResponseEntity<>(refreshToken, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>("Login failed.", HttpStatus.UNAUTHORIZED));
    }

    @PostMapping(ThemehubConstant.RESOURCE_USERS + "/logout")
    public Mono<ResponseEntity<String>> logoutUser(@RequestParam String username, @RequestParam String password) {
        return userService.logoutUser(username, password)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>("Logout failed or no active session found.", HttpStatus.UNAUTHORIZED));
    }


}
