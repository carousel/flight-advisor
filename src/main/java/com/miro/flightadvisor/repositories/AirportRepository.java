package com.miro.flightadvisor.repositories;

import com.miro.flightadvisor.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
    Optional<Airport> findByName(String name);
}
