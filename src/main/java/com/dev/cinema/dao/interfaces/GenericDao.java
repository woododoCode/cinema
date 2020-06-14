package com.dev.cinema.dao.interfaces;

import java.util.List;

public interface GenericDao<T> {
    T add(T t);

    List<T> getAll();

    T getById(Long id);
}
