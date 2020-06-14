package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.TicketDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Ticket;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Log4j2
@AllArgsConstructor
@Repository
public class TicketDaoImpl implements TicketDao {
    private final SessionFactory sessionFactory;

    @Override
    public Ticket add(Ticket ticket) {
        @Cleanup
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            log.info("Ticket with ID " + ticket.getId()
                    + "was successfully added to db");
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Ticket entity", e);
        }
    }

    @Override
    public List<Ticket> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Ticket> criteriaQuery =
                    session.getCriteriaBuilder().createQuery(Ticket.class);
            criteriaQuery.from(Ticket.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving Tickets. ", e);
        }
    }

    @Override
    public Ticket getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Ticket> criteriaQuery = criteriaBuilder.createQuery(Ticket.class);
            Root<Ticket> sessionRoot = criteriaQuery.from(Ticket.class);
            Predicate predicate = criteriaBuilder.equal(sessionRoot.get("id"), id);
            return session.createQuery(criteriaQuery.where(predicate)).getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to retrieve "
                    + "Ticket from DB with ID: " + id, e);
        }
    }
}

