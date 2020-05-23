package com.miro.flightadvisor.beans;

import com.miro.flightadvisor.entities.DaylightSavingsTime;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

public class RouteBean {

    private String airline;
    private Integer airlineId;
    private String sourceAirport;
    private Integer sourceAirportId;
    private String destinationAirport;
    private Integer destinationAirportId;
    private String codeshare;
    private Integer stops;
    private String equipment;
    private BigDecimal flightCost;

    private RouteBean(Builder builder) {
        airline = builder.airline;
        airlineId = builder.airlineId;
        sourceAirport = builder.sourceAirport;
        sourceAirportId = builder.sourceAirportId;
        destinationAirport = builder.destinationAirport;
        destinationAirportId = builder.destinationAirportId;
        codeshare = builder.codeshare;
        stops = builder.stops;
        equipment = builder.equipment;
        flightCost = builder.flightCost;
    }

    public String getAirline() {
        return airline;
    }

    public Integer getAirlineId() {
        return airlineId;
    }

    public String getSourceAirport() {
        return sourceAirport;
    }

    public Integer getSourceAirportId() {
        return sourceAirportId;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public Integer getDestinationAirportId() {
        return destinationAirportId;
    }

    public String getCodeshare() {
        return codeshare;
    }

    public Integer getStops() {
        return stops;
    }

    public String getEquipment() {
        return equipment;
    }

    public BigDecimal getFlightCost() {
        return flightCost;
    }

    public static class Builder {
        private String airline;
        private Integer airlineId;
        private String sourceAirport;
        private Integer sourceAirportId;
        private String destinationAirport;
        private Integer destinationAirportId;
        private String codeshare;
        private Integer stops;
        private String equipment;
        private BigDecimal flightCost;

        public Builder setAirline(String airline) {
            this.airline = airline;
            return this;
        }

        public Builder setAirlineId(String airlineId) {
            this.airlineId = Integer.parseInt(airlineId);
            return this;
        }

        public Builder setSourceAirport(String sourceAirport) {
            this.sourceAirport = sourceAirport;
            return this;
        }

        public Builder setSourceAirportId(String sourceAirportId) {
            this.sourceAirportId = Integer.parseInt(sourceAirportId);
            return this;
        }

        public Builder setDestinationAirport(String destinationAirport) {
            this.destinationAirport = destinationAirport;
            return this;
        }

        public Builder setDestinationAirportId(String destinationAirportId) {
            this.destinationAirportId = Integer.parseInt(destinationAirportId);
            return this;
        }

        public Builder setCodeshare(String codeshare) {
            if (codeshare.equals("unknown")) {
                this.codeshare = "";
            } else {
                this.codeshare = codeshare;
            }
            return this;
        }

        public Builder setStops(String stops) {
            this.stops = Integer.parseInt(stops);
            return this;
        }

        public Builder setEquipment(String equipment) {
            this.equipment = equipment;
            return this;
        }

        public Builder setFlightCost(String flightCost) {
            this.flightCost = new BigDecimal(flightCost);
            return this;
        }

        public RouteBean build() {
            return new RouteBean(this);
        }
    }
}