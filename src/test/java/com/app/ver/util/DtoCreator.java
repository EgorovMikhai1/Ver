package com.app.ver.util;

import com.app.ver.dto.CarDTO;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class DtoCreator {

    public static List<CarDTO> getExpectedCarDtoList() {
        return Arrays.asList(new CarDTO("Model S", "TESLA", "TESLA.00"),
                new CarDTO("Civic", "HONDA", "50.00"),
                new CarDTO("Camry", "TOYOTA", "65.00"),
                new CarDTO("Mustang", "FORD", "100.00"),
                new CarDTO("Golf", "VW", "55.00"));
    }
}