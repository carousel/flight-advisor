package com.miro.flightadvisor.services;

import com.miro.flightadvisor.beans.AirportBean;
import com.miro.flightadvisor.entities.Airport;
import com.miro.flightadvisor.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
/**
 * Main service for operations on airport data
 */
public class AirportService {

    AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public void saveAirportIfCityIsPresent(AirportBean airportBean) {
        Optional<Airport> airport = airportRepository.findByName(airportBean.getName());
        if (airport.isPresent()) {
            return;
        } else {
            Airport newAirport = new Airport(airportBean);
            airportRepository.save(newAirport);
        }
    }
}
