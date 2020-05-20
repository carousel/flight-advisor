package com.miro.flightadvisor.controllers;

import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Optional<List<City>> getCities() {
        return cityService.allCities();
    }

    @PostMapping("/cities")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addCity(@RequestBody @Valid City city) {
        cityService.addCity(city);
        return ResponseEntity.ok(city);
    }
}
