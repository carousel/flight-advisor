package com.miro.flightadvisor.controllers;

import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.services.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/open-cities")
    public Optional<List<City>> openCities() {
        return cityService.allCities();
    }

}
