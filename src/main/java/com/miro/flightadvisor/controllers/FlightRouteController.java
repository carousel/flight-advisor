package com.miro.flightadvisor.controllers;

import com.miro.flightadvisor.beans.CommentBean;
import com.miro.flightadvisor.entities.Comment;
import com.miro.flightadvisor.entities.Route;
import com.miro.flightadvisor.services.CityService;
import com.miro.flightadvisor.services.RouteService;
import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Api(description = "Fetch routes and calculate costs for flights")
@RestController
public class FlightRouteController {

    private RouteService routeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);


    @Autowired
    public FlightRouteController(RouteService routeService) {
        this.routeService = routeService;
    }


    @GetMapping("/routes/find-cheapest/{src}/{dest}")
    @ApiOperation(value = "return cheapest,highest, totall costs and route information")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Server error")})
    @PreAuthorize("hasRole('ROLE_USER')")
    public Optional<?> getCheapestFlight(@NotNull @PathVariable(value = "src") String src, @PathVariable(value = "dest") String dest) {
        LOGGER.debug(String.format("Fetching cheapest flight on %s", LocalDate.now()));
        return routeService.getCheapestFlight(src, dest);
    }


}
