package com.miro.flightadvisor.repositories;

import com.miro.flightadvisor.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query("select a.providedAirportId from Airport a where a.city=:city")
    List<Integer> getOnlyAirportId(String city);

    Optional<Airport> findByName(String name);

    Optional<List<Airport>> findByCity(String city);

    Optional<Airport> findByProvidedAirportId(Integer providedAirportId);
}
