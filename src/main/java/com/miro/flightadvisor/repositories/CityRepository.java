package com.miro.flightadvisor.repositories;

import com.miro.flightadvisor.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
