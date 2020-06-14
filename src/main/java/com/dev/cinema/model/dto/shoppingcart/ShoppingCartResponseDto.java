package com.dev.cinema.model.dto.shoppingcart;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShoppingCartResponseDto {
    private Long cartId;
    private List<Long> ticketsId;
    private Long userId;
}
