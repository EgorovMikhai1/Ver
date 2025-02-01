package com.app.ver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CarDTO {
    private String model;
    private String brand;
    private String pricePerDay;
}