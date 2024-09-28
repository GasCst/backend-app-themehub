package com.themehub.service;
import com.themehub.model.Cart;
import com.themehub.model.Theme;
import com.themehub.model.User;

public interface CartService {
    Cart getCart(User user);
    Cart addToCart(User user, Theme theme);
    Cart removeFromCart(User user, Theme theme);
    void clearCart(User user);
    Cart saveCart(Cart cart);
}