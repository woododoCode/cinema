package com.dev.cinema.controllers;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.moviesession.MovieSessionRequestDto;
import com.dev.cinema.model.dto.moviesession.MovieSessionResponseDto;
import com.dev.cinema.model.mappers.MovieSessionDtoMapper;
import com.dev.cinema.service.interfaces.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionDtoMapper mapper;

    @PostMapping
    public void addMovieSession(@RequestBody @Valid MovieSessionRequestDto requestDto) {
        movieSessionService.add(mapper.dtoToMovieSession(requestDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailableSession(
            @RequestParam Long movieId, @RequestParam @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE) LocalDate showTime) {
        List<MovieSession> availableSessions =
                movieSessionService.findAvailableSessions(movieId, showTime);
        return availableSessions.stream()
                .map(mapper::movieSessionToDto)
                .collect(Collectors.toList());
    }
}
