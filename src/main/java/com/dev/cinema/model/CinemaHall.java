package com.dev.cinema.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cinema_halls")
public class CinemaHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer capacity;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CinemaHall)) {
            return false;
        }
        CinemaHall that = (CinemaHall) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getCapacity(), that.getCapacity())
                && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCapacity(), getDescription());
    }

    @Override
    public String toString() {
        return "CinemaHall{"
                + "id=" + id
                + ", capacity=" + capacity
                + ", description='" + description
                + '}';
    }
}
