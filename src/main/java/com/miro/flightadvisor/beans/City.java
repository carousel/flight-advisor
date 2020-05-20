package com.miro.flightadvisor.beans;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class City {
    @NotNull
    private String name;
    @NotNull
    private String country;
    @NotNull
    private String description;

    public City(@NotNull String name, @NotNull String country, @NotNull String description) {
        this.name = name;
        this.country = country;
        this.description = description;
    }
}
