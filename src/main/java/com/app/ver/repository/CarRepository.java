package com.app.ver.repository;

import com.app.ver.entity.Car;
import com.app.ver.entity.enums.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByBrand(Brand brand);
    List<Car> findCarsByModel(String model);
}