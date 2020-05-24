package com.miro.flightadvisor.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CheapestFlight {
    private BigDecimal cheapest;
    private BigDecimal total;
    private List<Route> routes = new ArrayList<>();

    public CheapestFlight(BigDecimal cheapest, BigDecimal total, List<Route> routes) {
        this.cheapest = cheapest;
        this.total = total;
        this.routes = routes;
    }


}
