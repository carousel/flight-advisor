package com.miro.flightadvisor.entities;

import com.miro.flightadvisor.beans.AirportBean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "AIRPORT")
@NoArgsConstructor
@Getter
@Setter
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "airport_id")
    private Integer airportId;
    @Column(name = "name")
    private String name;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "IATA")
    private String iata;
    @Column(name = "ICAO")
    private String icao;
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Column(name = "longitude")
    private BigDecimal longitude;
    @Column(name = "altitude")
    private long altitude;
    @Column(name = "timezone")
    private Integer timezone;

    public DaylightSavingsTime getDst() {
        return dst;
    }

    public void setDst(DaylightSavingsTime dst) {
        this.dst = dst;
    }

    @Column(name = "dst")
    private DaylightSavingsTime dst;
    @Column(name = "tz")
    private String tz;
    @Column(name = "type")
    private String type;
    @Column(name = "source")
    private String source;

    public Airport(AirportBean airportBean) {
        setAirportId(airportBean.getAirportId());
        setName(airportBean.getName());
        setCity(airportBean.getCity());
        setCountry(airportBean.getCountry());

        setIata(airportBean.getIata());
        setIcao(airportBean.getIcao());

        setLatitude(airportBean.getLatitude());
        setLongitude(airportBean.getLongitude());
        setAltitude(airportBean.getAltitude());

        setTimezone(airportBean.getTimezone());
        setDst(airportBean.getDst());
        setTz(airportBean.getTz());

        setType(airportBean.getType());
        setSource(airportBean.getSource());
    }
}