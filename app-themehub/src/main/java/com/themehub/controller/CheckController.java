package com.themehub.controller;

import com.nimbusds.oauth2.sdk.TokenIntrospectionRequest;
import com.themehub.constant.ThemehubConstant;
import com.themehub.service.KeycloakService;
import com.themehub.service.impl.KeycloakServiceImpl;
import com.themehub.util.ThemeHubUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collection;


@RestController
@RequestMapping(ThemehubConstant.RESOURCE_GENERIC + "/check")
public class CheckController {

    @Autowired
    private KeycloakService keycloakService;




    @GetMapping("/simple")
    public ResponseEntity checkSimple() {
        return new ResponseEntity("Check status ok!", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_admin')")
    @GetMapping("/logged/admin")
    public ResponseEntity checkLogged() {
        return ResponseEntity.ok(true);
    }

    @PreAuthorize("hasAuthority('ROLE_customer')")
    @GetMapping("/logged/user")
    public ResponseEntity checkLogged1() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authorities: " + authentication.getAuthorities());

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            System.out.println("Role/Authority: " + authority.getAuthority());
        }
        return ResponseEntity.ok(true);
    }

    // Fetch access token based on username and password
    @GetMapping("/fetchToken")
    public Mono<String> fetchToken(@RequestParam String username, @RequestParam String password) {
        return keycloakService.getRefreshToken(username, password)
                .doOnNext(token -> System.out.println("Fetched Refresh Token: " + token));
    }

    @GetMapping("/getToken")
    public Mono<String> getToken(@RequestParam String username, @RequestParam String password) {
        return keycloakService.getToken(username, password)
                .doOnNext(token -> System.out.println("Fetched Token: " + token));
    }



    // Logout endpoint using refresh token
    @GetMapping("/logout")
    public Mono<String> performLogout(@RequestParam String username, @RequestParam String password) {
        return keycloakService.getRefreshToken(username, password)
                .flatMap(refreshToken -> {
                    System.out.println("Using Refresh Token: " + refreshToken);
                    return keycloakService.logout(refreshToken);
                });
    }
}