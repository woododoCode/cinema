package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.CinemaHallDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.CinemaHall;
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
public class CinemaHallDaoImpl implements CinemaHallDao {
    private final SessionFactory sessionFactory;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        @Cleanup
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
            log.info(String.format("Cinema hall  with id %s was successfully added to db",
                    cinemaHall.getId()));
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert cinemaHall entity", e);
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<CinemaHall> criteriaQuery =
                    session.getCriteriaBuilder().createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all CinemaHalls. ", e);
        }
    }

    @Override
    public CinemaHall getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CinemaHall> criteriaQuery =
                    criteriaBuilder.createQuery(CinemaHall.class);
            Root<CinemaHall> sessionRoot = criteriaQuery.from(CinemaHall.class);
            Predicate predicate = criteriaBuilder.equal(sessionRoot.get("id"), id);
            return session.createQuery(criteriaQuery.where(predicate)).getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to retrieve "
                    + "CinemaHall from DB with ID: " + id, e);
        }
    }
}
