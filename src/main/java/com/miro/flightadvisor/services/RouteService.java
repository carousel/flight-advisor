package com.miro.flightadvisor.services;

import ch.qos.logback.core.db.BindDataSourceToJNDIAction;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.miro.flightadvisor.beans.AirportBean;
import com.miro.flightadvisor.beans.RouteBean;
import com.miro.flightadvisor.entities.Airport;
import com.miro.flightadvisor.entities.CheapestFlight;
import com.miro.flightadvisor.entities.Route;
import com.miro.flightadvisor.exception.FlightAdvisorRuntimeException;
import com.miro.flightadvisor.repositories.AirportRepository;
import com.miro.flightadvisor.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.corba.Bridge;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final AirportRepository airportRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository, AirportRepository airportRepository) {
        this.routeRepository = routeRepository;
        this.airportRepository = airportRepository;
    }

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

    public Optional<List<Route>> allRoutes() {
        return Optional.of(routeRepository.findAll());
    }

    public Optional<CheapestFlight> getCheapestFlight(String src, String dest) {

        List<Integer> sources = airportRepository.getOnlyAirportId(src);
        List<Integer> destinations = airportRepository.getOnlyAirportId(dest);
        return calculateAndReturnData(sources, destinations);
    }

    public Optional<CheapestFlight> calculateAndReturnData(List<Integer> sources, List<Integer> destinations) {
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

            BigDecimal total = totalCosts.stream().reduce((i, j) -> i.add(j)).get();
            BigDecimal cheapest = totalCosts.stream().reduce((i, j) -> i.min(j)).get();

            CheapestFlight cheapestFlight = new CheapestFlight(cheapest, total, routes);
            return Optional.of(cheapestFlight);
        }
            BigDecimal total = new BigDecimal("0.0");
            BigDecimal cheapest = new BigDecimal("0.0");
            CheapestFlight cheapestFlight = new CheapestFlight(cheapest, total, routes);
            return Optional.of(cheapestFlight);

    }

}
