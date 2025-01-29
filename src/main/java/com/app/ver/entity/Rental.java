package com.app.ver.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class Rental {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalCost;

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