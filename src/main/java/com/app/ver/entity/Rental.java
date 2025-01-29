package com.app.ver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private int id;

    @Column(name = "rental_start_date")
    private LocalDate startDate;

    @Column(name = "rental_end_date")
    private LocalDate endDate;

    @Column(name = "rental_total_cost")
    private BigDecimal totalCost;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return id == rental.id && Objects.equals(startDate, rental.startDate) && Objects.equals(endDate, rental.endDate) && Objects.equals(totalCost, rental.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, totalCost);
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalCost=" + totalCost +
                '}';
    }
}