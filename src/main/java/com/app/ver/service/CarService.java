package com.app.ver.service;

import com.app.ver.dto.CarDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> getAllCars();
    List<CarDTO> getCarsByBrand(String brand);
    List<CarDTO> getCarsByModel(String model);
}