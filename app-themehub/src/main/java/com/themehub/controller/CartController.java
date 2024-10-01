package com.themehub.controller;

import com.themehub.constant.ThemehubConstant;
import com.themehub.model.Cart;
import com.themehub.model.Theme;
import com.themehub.model.User;
import com.themehub.repository.ThemeRepository;
import com.themehub.service.CartService;
import com.themehub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping(ThemehubConstant.RESOURCE_GENERIC)
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private UserService userService;


    // Ottieni la username dai claims JWT
    private String getUsernameFromJwt(Principal principal) {
        if (principal instanceof JwtAuthenticationToken) {
            Jwt jwt = ((JwtAuthenticationToken) principal).getToken();
            return jwt.getClaimAsString("preferred_username");
        }
        return null;
    }

    // Ottiene il carrello dell'utente corrente
    @GetMapping(ThemehubConstant.RESOURCE_CARTS + ThemehubConstant.RESOURCE_CARTS_CART)
    public ResponseEntity<Cart> getCart(Principal principal) {
        String username = getUsernameFromJwt(principal);
        if (username == null) {
            return ResponseEntity.badRequest().build(); // username non trovata
        }
        User user = userService.getUserByUsername(username);
        Cart cart = cartService.getCart(user);
        return ResponseEntity.ok(cart);
    }

    // Aggiunge un tema al carrello
    @GetMapping(ThemehubConstant.RESOURCE_CARTS + ThemehubConstant.RESOURCE_CARTS_CART+"/add/{themeId}")
    public ResponseEntity<Cart> addToCart(@PathVariable Long themeId, Principal principal) {
        String username = getUsernameFromJwt(principal);
        if (username == null) {
            return ResponseEntity.badRequest().build();  // Gestisci il caso in cui la username non venga trovata
        }

        User user = userService.getUserByUsername(username);
        Theme theme = this.themeRepository.findByIdtheme(themeId);
        Cart cart = cartService.addToCart(user, theme);
        return ResponseEntity.ok(cart);
    }

    // Rimuove un tema dal carrello
    @DeleteMapping(ThemehubConstant.RESOURCE_CARTS + ThemehubConstant.RESOURCE_CARTS_CART+"/remove/{themeId}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Long themeId, Principal principal) {
        String username = getUsernameFromJwt(principal);
        if (username == null) {
            return ResponseEntity.badRequest().build();  // Gestisci il caso in cui la username non venga trovata
        }

        User user = userService.getUserByUsername(username);
        Theme theme = this.themeRepository.findByIdtheme(themeId);
        Cart cart = cartService.removeFromCart(user, theme);
        return ResponseEntity.ok(cart);
    }

    // Svuota il carrello dell'utente
    @PostMapping(ThemehubConstant.RESOURCE_CARTS + ThemehubConstant.RESOURCE_CARTS_CART+"/clear")
    public ResponseEntity<Void> clearCart(Principal principal) {
        String username = getUsernameFromJwt(principal);
        if (username == null) {
            return ResponseEntity.badRequest().build();  // Gestisci il caso in cui la username non venga trovata
        }

        User user = userService.getUserByUsername(username);
        cartService.clearCart(user);
        return ResponseEntity.ok().build();
    }

    // Salva il carrello
    @PostMapping(ThemehubConstant.RESOURCE_CARTS + ThemehubConstant.RESOURCE_CARTS_CART+"/save")
    public ResponseEntity<Cart> saveCart(@RequestBody Cart cart) {
//        Cart savedCart = cartService.saveCart(cart);
//        return ResponseEntity.ok(savedCart);
        return null; // DA IMPLEMENTARE
    }
}