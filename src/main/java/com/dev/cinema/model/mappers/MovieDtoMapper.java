package com.dev.cinema.model.mappers;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.movie.MovieRequestDto;
import com.dev.cinema.model.dto.movie.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieDtoMapper {

    public Movie dtoToMovie(MovieRequestDto movieRequestDto) {
        var movie = new Movie();
        movie.setDescription(movieRequestDto.getDescription());
        movie.setTitle(movieRequestDto.getTitle());
        return movie;
    }

    public MovieResponseDto movieToDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setDescription(movie.getDescription());
        movieResponseDto.setTitle(movie.getTitle());
        return movieResponseDto;
    }
}
