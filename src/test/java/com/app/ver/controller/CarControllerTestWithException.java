package com.app.ver.controller;

import com.app.ver.exception.CarsNotExistInDataBaseException;
import com.app.ver.repository.CarRepository;
import com.app.ver.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"/db/schema-test.sql", "/db/data-test.sql"})
class CarControllerTestWithException {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarService carService;

    @MockitoBean
    public CarRepository carRepository;

    @Test
    void getAllCarsWithExceptionTest() throws Exception {
        when(carRepository.findAll()).thenReturn(List.of());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        System.out.println("Response: " + responseBody);

        Assertions.assertThrows(CarsNotExistInDataBaseException.class, () -> carService.getAllCars());
        Mockito.verify(carRepository, Mockito.times(2)).findAll();
    }
}