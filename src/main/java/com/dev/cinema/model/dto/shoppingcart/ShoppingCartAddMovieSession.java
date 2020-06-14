package com.dev.cinema.model.dto.shoppingcart;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShoppingCartAddMovieSession {
    private Long movieSessionId;
    private Long userId;
}
