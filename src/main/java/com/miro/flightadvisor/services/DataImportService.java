package com.miro.flightadvisor.services;

import com.miro.flightadvisor.beans.AirportBean;
import com.miro.flightadvisor.entities.Airport;
import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.entities.DaylightSavingsTime;
import com.miro.flightadvisor.repositories.AirportRepository;
import com.miro.flightadvisor.repositories.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class DataImportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataImportService.class);

    private final CityRepository cityRepository;
    private final AirportService airportService;

    @Autowired
    public DataImportService(CityRepository cityRepository, AirportService airportService) {
        this.cityRepository = cityRepository;
        this.airportService = airportService;
    }

    public void processFile(Path filePath) throws IOException {
        Files.lines(filePath, Charset.defaultCharset()).forEachOrdered(str -> {
            List<String> list = Arrays.asList(str.split("\\s*,\\s*"));
            if (list.size() > 1) {
                list.replaceAll(s -> {
                    return s.replace("\"\"", "bad")
                            .replace("\\N", "bad")
                            .replace("\"", "");

                });
                Optional<City> city = cityRepository.findByName(list.get(2));
                if (city.isPresent()) {
                    buildAirportObject(list);
                }
            }
        });

    }

    private void buildAirportObject(List<String> items) {
        String airportId = items.get(0);
        String name = items.get(1);
        String city = items.get(2);
        String country = items.get(3);
        String iata = items.get(4);
        String icao = items.get(5);
        String latitude = items.get(6);
        String longitude = items.get(7);
        String altitude = items.get(8);
        String timezone = items.get(9);
        String dst = items.get(10);
        String tz = items.get(11);
        String type = items.get(12);
        String source = items.get(13);


        AirportBean airportBean = new AirportBean.Builder()
                .setAirportId(airportId)
                .setName(name)
                .setCity(city)
                .setCountry(country)
                .setIata(iata)
                .setIcao(icao)
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setAltitude(altitude)
                .setTimezone(timezone)
                .setDst(dst)
                .setTz(tz)
                .setType(type)
                .setSource(source)
                .build();

        airportService.saveAirportIfCityIsPresent(airportBean);
    }
}
