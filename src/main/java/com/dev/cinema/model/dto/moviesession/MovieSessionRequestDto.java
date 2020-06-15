package com.dev.cinema.model.dto.moviesession;

import java.time.LocalDateTime;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieSessionRequestDto {
    @NotEmpty
    private Long movieId;
    @NotEmpty
    private Long cinemaHallId;
    @FutureOrPresent
    private LocalDateTime showTime;
}
