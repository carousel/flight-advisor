package com.miro.flightadvisor.beans;

import com.miro.flightadvisor.entities.Comment;
import lombok.Data;

import java.util.List;

@Data
/**
 * Main bean (DTO) for transferring data when creating fetching comments for city
 */
public class CityWithCommentsBean {
    public String name;
    public String country;
    public String description;
    public List<Comment> comments;
}
