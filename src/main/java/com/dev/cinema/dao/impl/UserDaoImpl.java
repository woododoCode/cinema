package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.UserDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.User;
import java.util.List;
import java.util.Optional;
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
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    @Override
    public User add(User user) {
        @Cleanup
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            log.info("User " + user
                    + " was successfully added to db");
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert User entity", e);
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<User> criteriaQuery =
                    session.getCriteriaBuilder().createQuery(User.class);
            criteriaQuery.from(User.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving MovieSessions. ", e);
        }
    }

    @Override
    public User getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> sessionRoot = criteriaQuery.from(User.class);
            Predicate predicate = criteriaBuilder.equal(sessionRoot.get("id"), id);
            return session.createQuery(criteriaQuery.where(predicate)).getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to retrieve "
                    + "User from DB with ID: " + id, e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder
                    .createQuery(User.class);
            Root<User> sessionRoot = criteriaQuery.from(User.class);
            Predicate predicate = criteriaBuilder
                    .equal(sessionRoot.get("email"), email);
            criteriaQuery.select(sessionRoot).where(predicate);
            return Optional.ofNullable(session.createQuery(criteriaQuery).uniqueResult());
        } catch (Exception e) {
            throw new DataProcessingException("Failed to retrieve user with email: " + email, e);
        }
    }
}
