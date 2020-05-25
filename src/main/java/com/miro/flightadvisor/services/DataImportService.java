package com.miro.flightadvisor.services;

import com.miro.flightadvisor.beans.AirportBean;
import com.miro.flightadvisor.beans.RouteBean;
import com.miro.flightadvisor.entities.Airport;
import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.repositories.AirportRepository;
import com.miro.flightadvisor.repositories.CityRepository;
import com.miro.flightadvisor.repositories.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


/**
 * Main service for importing airport and route data
 */
@Service
public class DataImportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataImportService.class);

    private final CityRepository cityRepository;
    private final AirportService airportService;
    private final AirportRepository airportRepository;
    private final RouteService routeService;

    @Autowired
    public DataImportService(CityRepository cityRepository, AirportService airportService, AirportRepository airportRepository, RouteService routeService) {
        this.cityRepository = cityRepository;
        this.airportService = airportService;
        this.airportRepository = airportRepository;
        this.routeService = routeService;

    }


    /**
     * main dispatcher for imported files (airport or route)
     * file is firstly uploaded, then parsed and data are extracted and used to build objects
     * after file is processed, file is deleted from file system
     * alternative is to process file while it is uploaded (without saving)
     * for better performance and sile possible huge file size, async executor is used
     *
     * @param filePath
     * @param filename
     * @throws IOException
     */
    @Async("importExecutor")
    public void processFile(Path filePath, String filename) throws IOException {
        if (filename.contains("airports")) {
            parseAirportFile(filePath);
        }
        if (filename.contains("routes")) {
            parseRouteFile(filePath);
        }
    }


    /**
     * pretty complex method for parsing airports file
     * contains many regex filters for cleaning and formatting broken data
     *
     * @param filePath
     * @throws IOException
     */
    private void parseAirportFile(Path filePath) throws IOException {
        try (
                Stream<String> airportStream = Files.lines(filePath, Charset.defaultCharset());
        ) {
            airportStream.forEachOrdered(airportLine -> {
                List<String> airport = Arrays.asList(airportLine.split("\\s*,\\s*"));
                if (airport.size() > 1) {
                    airport.replaceAll(s -> {
                        return s.replace("\"\"", "unknown")
                                .replace("\\N", "unknown")
                                .replace("\"", "")
                                .replace("\\", "unknown")
                                .replace("^$", "unknown")
                                .replace("ENEV", "unknown");
                    });
                    if (airport.size() == 14) {
                        Optional<City> city = cityRepository.findByName(airport.get(2));
                        try {
                            if (city.isPresent()) {
                                buildAirportObject(airport, filePath);
                            }
                        } catch (IOException e) {
                            LOGGER.error(e.getMessage(), e);
                        }
                    } else {
                        LOGGER.debug(String.format("Data is missing for airport %s", airport));
                    }
                }
            });
        }

    }

    /**
     * pretty complex method for parsing routes file
     * as suggested by SonarLint, this method should be refactored to decrease complexity
     * contains many regex filters for cleaning and formatting broken data
     *
     * @param filePath
     * @throws IOException
     */
    private void parseRouteFile(Path filePath) throws IOException {
        try (
                Stream<String> stream = Files.lines(filePath, Charset.defaultCharset());
        ) {
            stream.forEachOrdered(routeLine -> {
                List<String> route = Arrays.asList(routeLine.split("\\s*,\\s*"));
                if (route.size() > 1) {
                    route.replaceAll(s -> {
                        return s.replace("\"\"", "unknown")
                                .replace("\\N", "unknown")
                                .replace("\"", "")
                                .replace("\\", "unknown")
                                .replace("^$", "unknown")
                                .replace("ENEV", "unknown");


                    });
                    if (route.get(6).isEmpty()) {
                        route.set(6, "unknown");
                    }
                    ;


                    if (route.size() == 10) {
                        if (!route.get(3).equals("unknown")) {
                            Optional<Airport> sourceAirport = airportRepository.findByProvidedAirportId(Integer.parseInt(route.get(3)));
                            try {
                                if (sourceAirport.isPresent()) {
                                    buildRouteObject(route, filePath);
                                }
                            } catch (IOException e) {
                                LOGGER.error(e.getMessage(), e);
                            }
                        }

                        if (!route.get(5).equals("unknown")) {
                            Optional<Airport> destinationAirport = airportRepository.findByProvidedAirportId(Integer.parseInt(route.get(5)));
                            try {
                                if (destinationAirport.isPresent()) {
                                    buildRouteObject(route, filePath);
                                }
                            } catch (IOException e) {
                                LOGGER.error(e.getMessage(), e);
                            }
                        }
                    } else {
                        LOGGER.debug(String.format("Data is missing for route %s", route));
                    }

                }
            });
        }
    }

    /**
     * when airport file is processed and parsed, extracted data is used to build objects
     * this is done with the help of builders (builder pattern)
     * after that, objects are send to be saved in DB (through repository)
     *
     * @param items
     * @param filePath
     * @throws IOException
     */
    private void buildAirportObject(List<String> items, Path filePath) throws IOException {
        String providedAirportId = items.get(0);
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
                .setProvidedAirportId(providedAirportId)
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

    /**
     * when route file is processed and parsed, extracted data is used to build objects
     * this is done with the help of builders (builder pattern)
     * after that, objects are send to be saved in DB (through repository)
     *
     * @param items
     * @param filePath
     * @throws IOException
     */
    private void buildRouteObject(List<String> items, Path filePath) throws IOException {
        String airline = items.get(0);
        String airlineId = items.get(1);
        String sourceAirport = items.get(2);
        String sourceAirportId = items.get(3);
        String destinationAirport = items.get(4);
        String destinationAirportId = items.get(5);
        String codeshare = items.get(6);
        String stops = items.get(7);
        String equipment = items.get(8);
        String flightCost = items.get(9);

        RouteBean routeBean = new RouteBean.Builder()
                .setAirline(airline)
                .setAirlineId(airlineId)
                .setSourceAirport(sourceAirport)
                .setSourceAirportId(sourceAirportId)
                .setDestinationAirport(destinationAirport)
                .setDestinationAirportId(destinationAirportId)
                .setCodeshare(codeshare)
                .setStops(stops)
                .setEquipment(equipment)
                .setFlightCost(flightCost)
                .build();

        routeService.saveRoute(routeBean);

    }
}
