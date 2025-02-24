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

/**
 * Implementation of the {@link com.app.ver.service.CarService} interface.
 * <p>
 * This service class provides methods to retrieve car data from the database and convert them
 * into Data Transfer Objects (DTOs) using a mapper. It interacts with the {@code CarRepository} for
 * database operations and leverages {@code CarMapper} for entity-to-DTO conversion.
 * </p>
 */
@Service
public class CarServiceImpl implements CarService {

    /**
     * Repository for performing CRUD operations on {@link Car} entities.
     */
    private final CarRepository carRepository;

    /**
     * Mapper for converting {@link Car} entities to {@link CarDTO} objects.
     */
    private final CarMapper carMapper;

    /**
     * Constructs a new {@code CarServiceImpl} with the specified repository and mapper.
     *
     * @param carRepository the repository to interact with {@code Car} entities.
     * @param carMapper     the mapper to convert {@code Car} entities to {@code CarDTO} objects.
     */
    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    /**
     * Retrieves all cars from the database.
     * <p>
     * This method fetches all car records via the {@code CarRepository}. If no cars are found,
     * a {@link CarsNotExistInDataBaseException} is thrown.
     * </p>
     *
     * @return a list of {@link CarDTO} objects representing all available cars.
     * @throws CarsNotExistInDataBaseException if no car records exist in the database.
     */
    @Override
    public List<CarDTO> getAllCars() {
        List<Car> list = carRepository.findAll();
        if (list.isEmpty()) {
            throw new CarsNotExistInDataBaseException(ErrorMessage.CARS_NOT_EXIST_IN_DATABASE);
        }
        return carMapper.toCarDTOList(list);
    }

    /**
     * Retrieves cars by brand from the database.
     * <p>
     * This method converts the provided brand name to uppercase to match the {@link Brand} enum,
     * then queries the repository for cars of that brand. If no matching cars are found,
     * a {@link CarsNotExistInDataBaseException} is thrown.
     * </p>
     *
     * @param brand the brand name to filter cars by.
     * @return a list of {@link CarDTO} objects representing cars of the specified brand.
     * @throws CarsNotExistInDataBaseException if no cars with the specified brand exist in the database.
     */
    @Override
    public List<CarDTO> getCarsByBrand(String brand) {
        Brand brandUppercase = Brand.valueOf(brand.toUpperCase());
        List<Car> list = carRepository.findByBrand(brandUppercase);
        if (list.isEmpty()) {
            throw new CarsNotExistInDataBaseException(ErrorMessage.CARS_NOT_EXIST_IN_DATABASE);
        }
        return carMapper.toCarDTOList(list);
    }

    /**
     * Retrieves cars by model from the database.
     * <p>
     * This method queries the repository for cars matching the given model. If no matching cars
     * are found, a {@link CarsNotExistInDataBaseException} is thrown.
     * </p>
     *
     * @param model the model name to filter cars by.
     * @return a list of {@link CarDTO} objects representing cars of the specified model.
     * @throws CarsNotExistInDataBaseException if no cars with the specified model exist in the database.
     */
    @Override
    public List<CarDTO> getCarsByModel(String model) {
        List<Car> list = carRepository.findCarsByModel(model);
        if (list.isEmpty()) {
            throw new CarsNotExistInDataBaseException(ErrorMessage.CARS_NOT_EXIST_IN_DATABASE);
        }
        return carMapper.toCarDTOList(list);
    }
}