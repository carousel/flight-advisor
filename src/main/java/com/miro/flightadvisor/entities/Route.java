package com.miro.flightadvisor.entities;

import com.miro.flightadvisor.beans.RouteBean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ROUTE")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "airline", length = 3)
    private String airline;

    @Column(name = "airline_id")
    private Integer airlineId;

    @Column(name = "source_airport", length = 4)
    private String sourceAirport;
    @Column(name = "source_airport_id")
    private Integer sourceAirportId;

    @Column(name = "destination_airport", length = 4)
    private String destinationAirport;

    @Column(name = "destination_airport_id")
    private Integer destinationAirportId;

    @Column(columnDefinition = "varchar(10) default ' '")
    private String codeshare;

    @Column(name = "stops", length = 1)
    private Integer stops;

    @Column(name = "equipment", length = 3)
    private String equipment;

    @Column(name = "flight_cost")
    private BigDecimal flightCost;

    public Route(RouteBean routeBean) {
        setAirline(routeBean.getAirline());
        setAirlineId(routeBean.getAirlineId());
        setSourceAirport(routeBean.getSourceAirport());
        setSourceAirportId(routeBean.getSourceAirportId());
        setDestinationAirport(routeBean.getDestinationAirport());
        setDestinationAirportId(routeBean.getDestinationAirportId());
        setCodeshare(routeBean.getCodeshare());
        setStops(routeBean.getStops());
        setEquipment(routeBean.getEquipment());
        setFlightCost(routeBean.getFlightCost());
    }

}

