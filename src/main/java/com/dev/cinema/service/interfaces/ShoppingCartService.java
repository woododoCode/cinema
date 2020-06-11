package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;

public interface ShoppingCartService extends GenericService<ShoppingCart> {

    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
