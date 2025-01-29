package com.app.ver.entity;

import com.app.ver.entity.enums.Brand;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class Car {
    private int id;
    private String model;
    private Brand brand;
    private BigDecimal pricePerDay;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id
                && Objects.equals(model, car.model)
                && brand == car.brand
                && Objects.equals(pricePerDay, car.pricePerDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, brand, pricePerDay);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", brand=" + brand +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}