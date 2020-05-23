package com.miro.flightadvisor.repositories;

import com.miro.flightadvisor.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
    Optional<Route> findBySourceAirportIdAndDestinationAirportId(Integer sourceAirportId, Integer destinationAirportId);
}
