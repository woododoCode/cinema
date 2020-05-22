package com.dev.cinema.dao;

import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

@Dao
public interface MovieSessionDao extends GenericDao<MovieSession> {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
