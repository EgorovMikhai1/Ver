package com.app.ver.controller;

import com.app.ver.dto.CarDTO;
import com.app.ver.exception.CarsNotExistInDataBaseException;
import com.app.ver.repository.CarRepository;
import com.app.ver.service.CarService;
import com.app.ver.util.DtoCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = {"/db/schema.sql", "/db/data.sql"})
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // Очищает контекст перед каждым тестом
@TestPropertySource(locations = "classpath:application.yaml")
class CarControllerTest {

    @Autowired
    private  MockMvc mockMvc;

    @Autowired
    private  ObjectMapper objectMapper;

    @Autowired
    private CarService carService;

    @MockitoBean
    public CarRepository carRepository;

    private static final Random RANDOM = new Random();

    @Test
    void getAllCarsPositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String mockMvcResultAsString = result.getResponse().getContentAsString();

        List<CarDTO> actualList = objectMapper.readValue(mockMvcResultAsString, new TypeReference<>() {});
        List<CarDTO> expectedList = DtoCreator.getExpectedCarDtoList();

        CarDTO actualListCarDTO = actualList.get(RANDOM.nextInt(actualList.size()));
        String actualBrand = actualListCarDTO.getBrand();

        boolean hasParameterInExpectedList = false;

        for (CarDTO expectedCarDto : expectedList) {
            String expectedBrand = expectedCarDto.getBrand();

            if (expectedBrand.equals(actualBrand)) {
                hasParameterInExpectedList = true;
                break;
            }
        }

        Assertions.assertEquals(expectedList.size(), actualList.size());
        Assertions.assertTrue(hasParameterInExpectedList);
    }

    @Test
    void getAllCarsWithExceptionTest() throws Exception {
        when(carRepository.findAll()).thenReturn(List.of()); // Возвращаем пустой список

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        System.out.println("Response: " + responseBody);

        Assertions.assertThrows(CarsNotExistInDataBaseException.class, ()-> carService.getAllCars());
    }
}