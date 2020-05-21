package com.dev.cinema.service;

import com.dev.cinema.model.MovieSession;
import java.time.LocalDateTime;
import java.util.List;

public interface MovieSessionService extends GenericService<MovieSession> {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDateTime date);

}
