package com.dev.cinema.service.impl;

import com.dev.cinema.dao.interfaces.MovieDao;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.interfaces.MovieService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public Movie getById(Long id) {
        return movieDao.getById(id);
    }
}
