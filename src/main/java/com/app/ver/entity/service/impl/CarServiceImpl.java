package com.app.ver.entity.service.impl;

import com.app.ver.entity.Car;
import com.app.ver.entity.service.CarService;
import com.app.ver.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
}