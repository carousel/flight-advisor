package com.miro.flightadvisor.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Route assembler object/DTO
 * It is made out of data that is calculated for input source and destination cities
 * It route is present, this object will be constructed and returned to client
 */
@Data
@NoArgsConstructor
public class CheapestFlight {
    private BigDecimal cheapestFlightCost;
    private BigDecimal highestFlightCost;
    private BigDecimal totalCost;
    private Integer totalNumberOfRoutes;
    private List<Route> allRoutes = new ArrayList<>();

    public CheapestFlight(BigDecimal cheapestFlightCost, BigDecimal highestFlightCost, BigDecimal totalCost, List<Route> allRoutes, Integer totalNumberOfRoutes) {
        this.cheapestFlightCost = cheapestFlightCost;
        this.highestFlightCost = highestFlightCost;
        this.totalCost = totalCost;
        this.allRoutes = allRoutes;
        this.totalNumberOfRoutes = totalNumberOfRoutes;
    }
}
