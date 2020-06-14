package com.dev.cinema.model.dto.moviesession;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSessionResponseDto {
    private Long movieId;
    private Long cinemaHallId;
    private LocalDateTime showTime;
}
