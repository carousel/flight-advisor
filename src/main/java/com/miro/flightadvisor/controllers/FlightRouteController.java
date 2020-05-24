package com.miro.flightadvisor.controllers;

import com.miro.flightadvisor.beans.CommentBean;
import com.miro.flightadvisor.entities.Comment;
import com.miro.flightadvisor.entities.Route;
import com.miro.flightadvisor.services.CityService;
import com.miro.flightadvisor.services.RouteService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class FlightRouteController {

    private RouteService routeService;


    @Autowired
    public FlightRouteController(RouteService routeService) {
        this.routeService = routeService;
    }


    @GetMapping("/routes/find-cheapest/{src}/{dest}")
    public Optional<?> getCity(@NotNull @PathVariable(value = "src") String src, @PathVariable(value = "dest") String dest) {
        return routeService.getCheapestFlight(src, dest);
    }


}
