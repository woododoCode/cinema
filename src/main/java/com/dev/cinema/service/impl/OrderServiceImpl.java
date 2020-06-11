package com.dev.cinema.service.impl;

import com.dev.cinema.dao.interfaces.OrderDao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.interfaces.OrderService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setTickets(tickets);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getAllOrdersByUser(user);
    }

    @Override
    public Order add(Order order) {
        return orderDao.add(order);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order getById(Long id) {
        return orderDao.getById(id);
    }
}
