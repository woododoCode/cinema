package com.dev.cinema.controllers;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.order.OrderRequestDto;
import com.dev.cinema.model.dto.order.OrderResponseDto;
import com.dev.cinema.model.mappers.OrderMapper;
import com.dev.cinema.service.interfaces.OrderService;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import com.dev.cinema.service.interfaces.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;

    @PostMapping("/complete")
    public void completeOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        User user = userService.getById(orderRequestDto.getUserId());
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        orderService.completeOrder(shoppingCart.getTickets(),user);
    }

    @GetMapping
    public List<OrderResponseDto> getByUserId(Authentication authentication) {
        String login = authentication.getName();
        return orderService
                .getOrderHistory(userService.findByEmail(login))
                .stream()
                .map(orderMapper::orderToDto)
                .collect(Collectors.toList());
    }
}
