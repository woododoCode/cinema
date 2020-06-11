package com.dev.cinema.controllers;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.movie.MovieRequestDto;
import com.dev.cinema.model.dto.movie.MovieResponseDto;
import com.dev.cinema.model.mappers.MovieDtoMapper;
import com.dev.cinema.service.interfaces.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieDtoMapper movieDtoMapper;

    @GetMapping
    public List<MovieResponseDto> getMoviesList() {
        List<Movie> movies = movieService.getAll();
        return movies.stream()
                .map(movieDtoMapper::movieToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addMovie(@RequestBody MovieRequestDto requestDto) {
        movieService.add(movieDtoMapper.dtoToMovie(requestDto));
    }
}
