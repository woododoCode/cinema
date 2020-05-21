package com.dev.cinema.service.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDateTime date) {
        List<MovieSession> movieSessions = (List<MovieSession>) movieSessionDao.getAll()
                .stream()
                .filter(m -> m.getMovie().getId() == movieId
                        && m.getShowtime().isAfter(date))
                .collect(Collectors.toList());
        return movieSessions;
    }

    @Override
    public List<MovieSession> getAll() {
        return movieSessionDao.getAll();
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }
}
