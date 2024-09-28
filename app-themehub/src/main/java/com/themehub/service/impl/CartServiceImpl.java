package com.themehub.service.impl;

import com.themehub.model.Cart;
import com.themehub.model.Theme;
import com.themehub.model.User;
import com.themehub.repository.CartRepository;
import com.themehub.repository.ThemeRepository;
import com.themehub.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Override
    public Cart getCart(User user) {
        return cartRepository.findByUser(user).orElseGet(() -> new Cart(user));
    }

    @Override
    public Cart addToCart(User user, Theme theme) {
        Cart cart = getCart(user);
        cart.addTheme(theme);
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeFromCart(User user, Theme theme) {
        Cart cart = getCart(user);
        cart.removeTheme(theme);
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(User user) {
        Cart cart = getCart(user);
        cart.clearThemes();
        cartRepository.save(cart);
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

}
