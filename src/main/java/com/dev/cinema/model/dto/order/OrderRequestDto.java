package com.dev.cinema.model.dto.order;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequestDto {
    @NotEmpty
    private Long userId;
}
