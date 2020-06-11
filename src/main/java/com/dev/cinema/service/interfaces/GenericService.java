package com.dev.cinema.service.interfaces;

import java.util.List;

public interface GenericService<T> {
    T add(T t);

    List<T> getAll();

    T getById(Long id);
}
