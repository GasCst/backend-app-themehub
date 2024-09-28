package com.themehub.controller;

import com.themehub.constant.ThemehubConstant;
import com.themehub.model.Cart;
import com.themehub.model.Theme;
import com.themehub.model.User;
import com.themehub.service.CartService;
import com.themehub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(ThemehubConstant.RESOURCE_GENERIC)
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    // Ottiene il carrello dell'utente corrente
    @GetMapping
    public ResponseEntity<Cart> getCart(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        Cart cart = cartService.getCart(user);
        return ResponseEntity.ok(cart);
    }

    // Aggiunge un tema al carrello
    @PostMapping("/add/{themeId}")
    public ResponseEntity<Cart> addToCart(@PathVariable Long themeId, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        Theme theme = new Theme();  // Recupera il tema dal repository, se necessario
        theme.setIdtheme(themeId);
        Cart cart = cartService.addToCart(user, theme);
        return ResponseEntity.ok(cart);
    }

    // Rimuove un tema dal carrello
    @DeleteMapping("/remove/{themeId}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Long themeId, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        Theme theme = new Theme();  // Recupera il tema dal repository, se necessario
        theme.setIdtheme(themeId);
        Cart cart = cartService.removeFromCart(user, theme);
        return ResponseEntity.ok(cart);
    }

    // Svuota il carrello dell'utente
    @PostMapping("/clear")
    public ResponseEntity<Void> clearCart(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        cartService.clearCart(user);
        return ResponseEntity.ok().build();
    }

    // Salva il carrello
    @PostMapping("/save")
    public ResponseEntity<Cart> saveCart(@RequestBody Cart cart) {
        Cart savedCart = cartService.saveCart(cart);
        return ResponseEntity.ok(savedCart);
    }
}