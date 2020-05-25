package com.miro.flightadvisor.controllers;

import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.services.CityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CityBeanControllerTest {

    private static final String BASE_URL = "http://localhost/8080";

    @MockBean
    private CityService cityService;

    @LocalServerPort
    private int port;

    @Mock
    private City city;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private CityController cityController;

    @Test
    void weHaveController() {
        assertThat(cityController).isNotNull();
    }

    @Test
    void resultContainsCity() {
        assertThat(this.testRestTemplate.getForObject(BASE_URL + "/cities", String.class)).contains("Amsterdam");
    }

    @Test
    void cityCanBeFetchedByName() {
        assertThat(this.testRestTemplate.getForObject(BASE_URL + "/cities/Amsterdam", String.class)).contains("Amsterdam");
    }

    @Test
    void commentForCityCanBeFetchedAndLimited() {
        assertThat(this.testRestTemplate.getForObject(BASE_URL + "/cities/Amsterdam?cq=1", String.class)).contains("Amsterdam comment");
    }
}