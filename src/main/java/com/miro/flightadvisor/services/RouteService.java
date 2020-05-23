package com.miro.flightadvisor.services;

import com.miro.flightadvisor.beans.AirportBean;
import com.miro.flightadvisor.beans.RouteBean;
import com.miro.flightadvisor.entities.Airport;
import com.miro.flightadvisor.entities.Route;
import com.miro.flightadvisor.repositories.AirportRepository;
import com.miro.flightadvisor.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
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
}
