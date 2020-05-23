package com.miro.flightadvisor.beans;

import com.miro.flightadvisor.entities.DaylightSavingsTime;

import java.math.BigDecimal;

public class AirportBean {
    private final Integer airportId;
    private final String name;
    private final String city;
    private final String country;
    private final String iata;
    private final String icao;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final Integer altitude;
    private final Double timezone;
    private final DaylightSavingsTime dst;
    private final String tz;
    private final String type;
    private final String source;

    private AirportBean(Builder builder) {
        airportId = builder.airportId;
        name = builder.name;
        city = builder.city;
        country = builder.country;

        iata = builder.iata;
        icao = builder.icao;

        latitude = builder.latitude;
        longitude = builder.longitude;
        altitude = builder.altitude;

        timezone = builder.timezone;
        dst = builder.dst;
        tz = builder.tz;

        type = builder.type;
        source = builder.source;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public Double getTimezone() {
        return timezone;
    }

    public DaylightSavingsTime getDst() {
        return dst;
    }

    public Integer getAirportId() {
        return airportId;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getIata() {
        return iata;
    }

    public String getIcao() {
        return icao;
    }

    public String getTz() {
        return tz;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public static class Builder {
        private Integer airportId;
        private String name;
        private String city;
        private String country;

        private BigDecimal latitude;
        private BigDecimal longitude;
        private Integer altitude;
        private Double timezone;
        private DaylightSavingsTime dst;

        private String tz;
        private String type;
        private String source;
        private String iata;
        private String icao;

        public Builder setAirportId(String airportId) {
            this.airportId = Integer.parseInt(airportId);
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder setIata(String iata) {
            this.iata = iata;
            return this;
        }

        public Builder setIcao(String icao) {
            this.icao = icao;
            return this;
        }


        public Builder setLatitude(String latitude) {
            if (latitude.equals("unknown")) {
                this.latitude = new BigDecimal(0);
                return this;
            } else {
                this.latitude = new BigDecimal(latitude);
            }
            return this;
        }

        public Builder setLongitude(String longitude) {
            if (longitude.equals("unknown")) {
                this.longitude = new BigDecimal(0);
                return this;
            } else {
                this.longitude = new BigDecimal(longitude);
            }
            return this;
        }

        public Builder setAltitude(String altitude) {
            if (altitude.equals("unknown")) {
                this.altitude = 0;
                return this;
            } else {
                this.altitude = Integer.parseInt(altitude);
            }
            return this;
        }

        public Builder setTimezone(String timezone) {
            if (timezone.equals("unknown")) {
                this.timezone = 0.0;
                return this;
            } else {
                this.timezone = Double.parseDouble(timezone);
            }
            return this;
        }

        public Builder setDst(String dst) {
            if (dst.equals("unknown")) {
                this.dst = DaylightSavingsTime.N;
                return this;
            } else {
                this.dst = DaylightSavingsTime.valueOf(dst);
            }
            return this;
        }


        public Builder setTz(String tz) {
            if (tz.equals("unknwon")) {
                this.tz = "Unknown";
                return this;
            } else {
                this.tz = tz;
            }
            return this;
        }


        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setSource(String source) {
            this.source = source;
            return this;
        }

        public AirportBean build() {
            return new AirportBean(this);
        }
    }
}