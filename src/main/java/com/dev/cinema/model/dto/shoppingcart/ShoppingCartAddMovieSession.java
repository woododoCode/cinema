package com.dev.cinema.model.dto.shoppingcart;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShoppingCartAddMovieSession {
    @NotEmpty
    private Long movieSessionId;
    @NotEmpty
    private Long userId;
}
