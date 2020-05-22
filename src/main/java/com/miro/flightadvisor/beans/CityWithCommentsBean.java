package com.miro.flightadvisor.beans;

import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.entities.Comment;

import java.util.List;

public class CityWithCommentsBean {
    public String name;
    public String country;
    public String description;
    public List<Comment> comments;
}
