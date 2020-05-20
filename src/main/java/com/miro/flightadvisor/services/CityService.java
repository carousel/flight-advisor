package com.miro.flightadvisor.services;

import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void addCity(City city) {
        this.cityRepository.save(city);
    }

    public Optional<List<City>> allCities() {
        return Optional.of(this.cityRepository.findAll());
    }
}
