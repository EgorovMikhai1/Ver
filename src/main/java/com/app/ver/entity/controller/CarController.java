package com.app.ver.entity.controller;

import com.app.ver.entity.Car;
import com.app.ver.entity.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/getAll")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }
}