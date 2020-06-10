package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class MovieSessionDaoImpl implements MovieSessionDao {
    private final SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> criteriaQuery = criteriaBuilder
                    .createQuery(MovieSession.class);
            Root<MovieSession> sessionRoot = criteriaQuery.from(MovieSession.class);
            Predicate idPredicate = criteriaBuilder
                    .equal(sessionRoot.get("movie"), movieId);
            Predicate datePredicate = criteriaBuilder
                    .greaterThan(sessionRoot.get("showtime"), date.atStartOfDay());
            criteriaQuery.where(idPredicate, datePredicate);
            return session.createQuery(criteriaQuery).list();
        } catch (Exception e) {
            throw new DataProcessingException(String
                    .format("Failed to retrieve movie sessions by movie id:%s and on date:%s",
                            movieId, date), e);
        }
    }

    @Override
    public List<MovieSession> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<MovieSession> criteriaQuery =
                    session.getCriteriaBuilder().createQuery(MovieSession.class);
            criteriaQuery.from(MovieSession.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving MovieSessions. ", e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        @Cleanup
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            log.info(String.format("Movie session with id: %s was successfully added to db",
                    movieSession.getId()));
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert MovieSession entity", e);
        }
    }
}
