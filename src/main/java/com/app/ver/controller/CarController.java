package com.app.ver.controller;

import com.app.ver.dto.CarDTO;
import com.app.ver.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/getAll")
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/getByBrand/{brand}")
    public List<CarDTO> getCarsByBrand(@PathVariable String brand) {
        return carService.getCarsByBrand(brand);
    }

    @GetMapping("/getByModel/{model}")
    public List<CarDTO> getCarsByModel(@PathVariable String model) {
        return carService.getCarsByModel(model);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}