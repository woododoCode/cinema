package com.dev.cinema.dao.interfaces;

import com.dev.cinema.model.Ticket;

public interface TicketDao extends GenericDao<Ticket> {
    Ticket add(Ticket ticket);
}
