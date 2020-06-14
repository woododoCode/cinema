package com.dev.cinema.model.dto.cinemahall;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CinemaHallResponseDto {
    private int capacity;
    private String description;
    private Long cinemaHallId;
}
