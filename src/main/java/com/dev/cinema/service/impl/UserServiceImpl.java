package com.dev.cinema.service.impl;

import com.dev.cinema.dao.interfaces.UserDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email).orElse(null);
    }

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
