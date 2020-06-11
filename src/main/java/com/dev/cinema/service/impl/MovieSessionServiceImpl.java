package com.dev.cinema.service.impl;

import com.dev.cinema.dao.interfaces.MovieSessionDao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.interfaces.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public List<MovieSession> getAll() {
        return movieSessionDao.getAll();
    }

    @Override
    public MovieSession getById(Long id) {
        return movieSessionDao.getById(id);
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }
}
