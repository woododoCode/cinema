package com.dev.cinema.security;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDB = userService.findByEmail(email);
        if (userFromDB != null) {
            boolean passwordEquals = userFromDB.getPassword()
                    .equals(HashUtil.hashPassword(password, userFromDB.getSalt()));
            if (passwordEquals) {
                return userFromDB;
            }
        }
        throw new AuthenticationException("Incorrect login or password");
    }

    public User register(String email, String password) {
        var user = new User();
        byte[] salt = HashUtil.getSalt();
        user.setSalt(salt);
        user.setPassword(HashUtil.hashPassword(password, salt));
        user.setEmail(email);
        shoppingCartService.registerNewShoppingCart(userService.add(user));
        return user;
    }
}
