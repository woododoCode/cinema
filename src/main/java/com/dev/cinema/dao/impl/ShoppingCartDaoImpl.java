package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.ShoppingCartDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import java.util.Optional;
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
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private final SessionFactory sessionFactory;

    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        @Cleanup
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            log.info(String.format("Shopping Cart with id %s was successfully added to db",
                    shoppingCart.getId()));
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Shopping Cart entity", e);
        }
    }

    @Override
    public Optional<ShoppingCart> getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ShoppingCart> criteriaQuery = criteriaBuilder
                    .createQuery(ShoppingCart.class);
            Root<ShoppingCart> sessionRoot = criteriaQuery.from(ShoppingCart.class);
            Predicate predicate = criteriaBuilder.equal(sessionRoot.get("user"), user);
            sessionRoot.fetch("tickets", JoinType.LEFT);
            return session.createQuery(criteriaQuery.where(predicate)).uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to retrieve "
                    + "shopping cart from user with ID: " + user.getId(), e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        @Cleanup
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
            log.info(String
                    .format("Shopping Cart with id %s was successfully updated",
                            shoppingCart.getId()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Shopping Cart entity", e);
        }
    }
}
