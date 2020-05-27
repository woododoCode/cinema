package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.User;

public interface UserService extends GenericService<User> {
    User findByEmail(String email);
}
