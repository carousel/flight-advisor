package com.miro.flightadvisor;

import com.miro.flightadvisor.entities.Airport;
import com.miro.flightadvisor.repositories.AirportRepository;
import com.miro.flightadvisor.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class FlightAdvisorServiceApplication {

    @Autowired
    private static AirportRepository airportRepository;

    public static void main(String[] args) {
        SpringApplication.run(FlightAdvisorServiceApplication.class, args);
    }
}
