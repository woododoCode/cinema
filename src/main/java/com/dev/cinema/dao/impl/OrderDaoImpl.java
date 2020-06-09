package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.OrderDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
public class OrderDaoImpl implements OrderDao {
    private final SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        @Cleanup
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            log.info(String.format("Order with id %s was successfully added to db",
                    order.getId()));
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert order entity", e);
        }
    }

    @Override
    public List<Order> getAllOrdersByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = criteriaBuilder
                    .createQuery(Order.class);
            Root<Order> sessionRoot = criteriaQuery.from(Order.class);
            Predicate predicate = criteriaBuilder.equal(sessionRoot.get("user"), user);
            sessionRoot.fetch("tickets", JoinType.LEFT);
            return session.createQuery(criteriaQuery.where(predicate)).list();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to retrieve "
                    + "shopping cart from user with ID: " + user.getId(), e);
        }
    }
}