package com.miro.flightadvisor.services;

import com.miro.flightadvisor.beans.CityBean;
import com.miro.flightadvisor.beans.CommentBean;
import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.entities.Comment;
import com.miro.flightadvisor.exception.FlightAdvisorRuntimeException;
import com.miro.flightadvisor.repositories.CityRepository;
import com.miro.flightadvisor.repositories.CommentRepository;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.apache.commons.lang3.text.translate.NumericEntityUnescaper;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private CityRepository cityRepository;
    private CommentRepository commentRepository;

    public CityService(CityRepository cityRepository, CommentRepository commentRepository) {
        this.cityRepository = cityRepository;
        this.commentRepository = commentRepository;
    }

    public void addCity(CityBean cityBean) {
        City city = new City();
        city.setCountry(cityBean.getCountry());
        city.setDescription(cityBean.getDescription());
        city.setName(cityBean.getName());
        this.cityRepository.save(city);
    }

    public Optional<List<City>> allCities() {
        return Optional.of(this.cityRepository.findAll());
    }

    public Optional<List<Comment>> allCommentsForCity() {
        Optional<City> city = cityRepository.findById(1);
        return Optional.of(city.get().getComments());
    }

    public void addCommentForCity(CommentBean commentBean) {
        Comment comment = new Comment();
        Integer cityId = Integer.parseInt(commentBean.getCityId());
        City city = cityRepository.findById(cityId).orElseThrow(() -> new FlightAdvisorRuntimeException("We can't find a city"));
        comment.setBody(commentBean.getBody());
        comment.setCity(city);
        commentRepository.save(comment);
    }
    public void deleteCommentForCity(String inputCityId, String inputCommentId) {
        Integer cityId = Integer.parseInt(inputCityId);
        Integer commentId = Integer.parseInt(inputCommentId);
        City city = cityRepository.findById(cityId).orElseThrow(() -> new FlightAdvisorRuntimeException("We can't find a city"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new FlightAdvisorRuntimeException("We can't find a comment"));
        commentRepository.delete(comment);
    }
    public void updateCommentForCity(String inputCityId, String inputCommentId, CommentBean commentBean) {
        Integer cityId = Integer.parseInt(inputCityId);
        Integer commentId = Integer.parseInt(inputCommentId);
        City city = cityRepository.findById(cityId).orElseThrow(() -> new FlightAdvisorRuntimeException("We can't find a city"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new FlightAdvisorRuntimeException("We can't find a comment"));
        comment.setBody(commentBean.getBody());
        commentRepository.save(comment);
    }

}
