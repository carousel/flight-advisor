package com.miro.flightadvisor.repositories;

import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findByName(String name);
}