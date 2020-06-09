package com.dev.cinema.security;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import com.dev.cinema.service.interfaces.UserService;
import com.dev.cinema.util.HashUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

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
