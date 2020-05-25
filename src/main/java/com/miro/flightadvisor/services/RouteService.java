package com.miro.flightadvisor.services;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.miro.flightadvisor.beans.RouteBean;
import com.miro.flightadvisor.entities.CheapestFlight;
import com.miro.flightadvisor.entities.Route;
import com.miro.flightadvisor.repositories.AirportRepository;
import com.miro.flightadvisor.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


/**
 * Main service for managing routes calculation, based on imported airports and cities
 */
@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final AirportRepository airportRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository, AirportRepository airportRepository) {
        this.routeRepository = routeRepository;
        this.airportRepository = airportRepository;
    }

    /**
     * main service for saving parsed and processed route file into DB
     *
     * @param routeBean
     */
    public void saveRoute(RouteBean routeBean) {
        Integer sourceAirportId = routeBean.getSourceAirportId();
        Integer destinationAirportId = routeBean.getDestinationAirportId();
        Optional<Route> route = routeRepository.findBySourceAirportIdAndDestinationAirportId(sourceAirportId, destinationAirportId);
        if (route.isPresent()) {
            return;
        } else {
            Route newRoute = new Route(routeBean);
            routeRepository.save(newRoute);
        }
    }

    /**
     * Main entry point method for calculation cheapest route
     * it is dispatching most of its work to "calculateAndReturnData" method, after fetching cities by name from URI
     *
     * @param src
     * @param dest
     * @return
     */
    public Optional<CheapestFlight> getCheapestFlight(String src, String dest) {
        List<Integer> sources = airportRepository.getOnlyAirportId(src);
        List<Integer> destinations = airportRepository.getOnlyAirportId(dest);
        return calculateAndReturnData(sources, destinations);
    }

    /**
     * This method is used to assemble and build final "CheapestFlight" object
     * it result is present, final object is returned to client
     *
     * @param sources
     * @param destinations
     * @return
     */
    private Optional<CheapestFlight> calculateAndReturnData(List<Integer> sources, List<Integer> destinations) {
        List<List<Integer>> airportIds = Lists.cartesianProduct(ImmutableList.of(
                sources,
                destinations));
        List<Route> routes = new ArrayList<>();
        airportIds.stream().forEachOrdered(a -> {
            Integer source = a.get(0);
            Integer destination = a.get(1);
            Optional<Route> route = routeRepository.findBySourceAirportIdAndDestinationAirportId(source, destination);
            if (route.isPresent()) {
                routes.add(route.get());
            }
        });

        if (routes.size() > 0) {
            List<BigDecimal> totalCosts = new ArrayList<>();

            routes.stream().forEach(r -> {
                totalCosts.add(r.getFlightCost());
            });
            BigDecimal totalFlightCost = totalCosts.stream().reduce((i, j) -> i.add(j)).get();
            BigDecimal cheapestFlightCost = totalCosts.stream().reduce((i, j) -> i.min(j)).get();
            BigDecimal highestFlightCost = totalCosts.stream().reduce((i, j) -> i.max(j)).get();
            Integer totalNumberOfRoutes = routes.size();

            CheapestFlight cheapestFlight = new CheapestFlight(cheapestFlightCost, highestFlightCost, totalFlightCost, routes, totalNumberOfRoutes);
            return Optional.of(cheapestFlight);
        }
        BigDecimal totalFlightCost = new BigDecimal("0.0");
        BigDecimal cheapestFlightCost = new BigDecimal("0.0");
        BigDecimal highestFlightCost = new BigDecimal("0.0");
        Integer totalNumberOfRoutes = routes.size();
        CheapestFlight cheapestFlight = new CheapestFlight(cheapestFlightCost, highestFlightCost, totalFlightCost, routes, totalNumberOfRoutes);
        return Optional.of(cheapestFlight);
    }

}
