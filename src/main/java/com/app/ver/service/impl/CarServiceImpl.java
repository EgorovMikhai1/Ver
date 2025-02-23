package com.app.ver.service.impl;

import com.app.ver.dto.CarDTO;
import com.app.ver.entity.Car;
import com.app.ver.entity.enums.Brand;
import com.app.ver.exception.CarsNotExistInDataBaseException;
import com.app.ver.exception.errorMessages.ErrorMessage;
import com.app.ver.mapper.CarMapper;
import com.app.ver.repository.CarRepository;
import com.app.ver.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public List<CarDTO> getAllCars() {
        List<Car> list = carRepository.findAll();

        if (list.isEmpty()) {
            throw new CarsNotExistInDataBaseException(ErrorMessage.CARS_NOT_EXIST_IN_DATABASE);
        }
        return carMapper.toCarDTOList(list);
    }

    @Override
    public List<CarDTO> getCarsByBrand(String brand) {
        Brand brandUppercase = Brand.valueOf(brand.toUpperCase());
        List<Car> list = carRepository.findByBrand(brandUppercase);

        if (list.isEmpty()) {
            throw new CarsNotExistInDataBaseException(ErrorMessage.CARS_NOT_EXIST_IN_DATABASE);
        }
        return carMapper.toCarDTOList(list);
    }

    @Override
    public List<CarDTO> getCarsByModel(String model) {
        List<Car> list = carRepository.findCarsByModel(model);
        if (list.isEmpty()) {
            throw new CarsNotExistInDataBaseException(ErrorMessage.CARS_NOT_EXIST_IN_DATABASE);
        }
        return carMapper.toCarDTOList(list);
    }
}