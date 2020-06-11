package com.dev.cinema.service.impl;

import com.dev.cinema.dao.interfaces.CinemaHallDao;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.interfaces.CinemaHallService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    private final CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }

    @Override
    public CinemaHall getById(Long id) {
        return cinemaHallDao.getById(id);
    }
}
