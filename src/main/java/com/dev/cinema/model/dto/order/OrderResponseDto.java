package com.dev.cinema.model.dto.order;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResponseDto {

    private Long orderId;
    private List<Long> ticketId;
    private Long userId;
    private LocalDateTime orderDate;
}
