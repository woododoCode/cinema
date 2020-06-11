package com.dev.cinema.dao.interfaces;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    Order add(Order order);

    List<Order> getAllOrdersByUser(User user);
}
