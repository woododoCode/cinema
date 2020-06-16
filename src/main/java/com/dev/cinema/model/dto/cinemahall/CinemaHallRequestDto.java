package com.dev.cinema.model.dto.cinemahall;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CinemaHallRequestDto {
    @Positive
    private int capacity;
    @NotEmpty
    @Size(max = 256)
    private String description;
}
