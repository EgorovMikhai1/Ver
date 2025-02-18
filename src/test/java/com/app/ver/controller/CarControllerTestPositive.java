package com.app.ver.controller;

import com.app.ver.dto.CarDTO;
import com.app.ver.util.DtoCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Random;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"/db/schema-test.sql", "/db/data-test.sql"})
@WithMockUser(value = "ADMIN", password = "qqq", roles = {"USER", "ADMIN"})
class CarControllerTestPositive {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Random RANDOM = new Random();

    @Test
    void getAllCarsPositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String mockMvcResultAsString = result.getResponse().getContentAsString();

        List<CarDTO> actualList = objectMapper.readValue(mockMvcResultAsString, new TypeReference<>() {
        });
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
    void getAllCarsByBrandPositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getByBrand/VW")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String mockMvcResultAsString = result.getResponse().getContentAsString();
        List<CarDTO> actualList = objectMapper.readValue(mockMvcResultAsString, new TypeReference<>() {});

        CarDTO expectedData = new CarDTO("Golf", "VW", "55.00");
        CarDTO actualData = actualList.get(0);

        Assertions.assertEquals(1, actualList.size());
        Assertions.assertEquals(expectedData.getModel(), actualData.getModel());
        Assertions.assertEquals(expectedData.getBrand(), actualData.getBrand());
        Assertions.assertEquals(expectedData.getPricePerDay(), actualData.getPricePerDay());
    }

    @Test
    void getAllCarsByModelPositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getByModel/Golf")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String mockMvcResultAsString = result.getResponse().getContentAsString();
        List<CarDTO> actualList = objectMapper.readValue(mockMvcResultAsString, new TypeReference<>() {});

        CarDTO expectedData = new CarDTO("Golf", "VW", "55.00");
        CarDTO actualData = actualList.get(0);

        Assertions.assertEquals(1, actualList.size());
        Assertions.assertEquals(expectedData.getModel(), actualData.getModel());
        Assertions.assertEquals(expectedData.getBrand(), actualData.getBrand());
        Assertions.assertEquals(expectedData.getPricePerDay(), actualData.getPricePerDay());
    }
}