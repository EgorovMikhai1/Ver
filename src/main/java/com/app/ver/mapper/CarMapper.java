package com.app.ver.mapper;

import com.app.ver.dto.CarDTO;
import com.app.ver.entity.Car;

import java.util.List;

public interface CarMapper {
    List<CarDTO> toCarDTOList(List<Car> carList);
}