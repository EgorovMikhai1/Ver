package com.app.ver.mapper.impl;

import com.app.ver.dto.CarDTO;
import com.app.ver.entity.Car;
import com.app.ver.mapper.CarMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarMapperImpl implements CarMapper {

    @Override
    public List<CarDTO> toCarDTOList(List<Car> carList) {
        System.out.println("public List<CarDTO> toCarDTOList(List<Car> carList) {");
        List<CarDTO> carDTOList = new ArrayList<>();

        for (Car car : carList) {
            System.out.println(" for (Car car : carList) {");
            String model = car.getModel();
            String brand = car.getBrand().toString();
            String pricePerDay = String.valueOf(car.getPricePerDay());

            carDTOList.add(new CarDTO(model, brand, pricePerDay));
        }
        System.out.println(" return carDTOList;");
        return carDTOList;
    }
}