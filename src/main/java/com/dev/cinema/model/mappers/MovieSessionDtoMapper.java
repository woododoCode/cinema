package com.dev.cinema.model.mappers;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.moviesession.MovieSessionRequestDto;
import com.dev.cinema.model.dto.moviesession.MovieSessionResponseDto;
import com.dev.cinema.service.interfaces.CinemaHallService;
import com.dev.cinema.service.interfaces.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MovieSessionDtoMapper {
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;

    public MovieSession dtoToMovieSession(MovieSessionRequestDto requestDto) {
        var movieSession = new MovieSession();
        movieSession.setMovie(movieService.getById(requestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.getById(requestDto.getCinemaHallId()));
        movieSession.setShowtime(requestDto.getShowTime());
        return movieSession;
    }

    public MovieSessionResponseDto movieSessionToDto(MovieSession movieSession) {
        var movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setShowTime(movieSession.getShowtime());
        return movieSessionResponseDto;
    }
}
