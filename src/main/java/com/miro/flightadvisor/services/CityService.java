package com.miro.flightadvisor.services;

import com.miro.flightadvisor.beans.CityBean;
import com.miro.flightadvisor.beans.CityWithCommentsBean;
import com.miro.flightadvisor.beans.CommentBean;
import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.entities.Comment;
import com.miro.flightadvisor.exception.FlightAdvisorRuntimeException;
import com.miro.flightadvisor.repositories.CityRepository;
import com.miro.flightadvisor.repositories.CommentRepository;
import com.miro.flightadvisor.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
/**
 * Main service for operations on city data
 * method names for internal operations are self descriptive
 */
public class CityService {
    private CityRepository cityRepository;
    private CommentRepository commentRepository;
    private RouteRepository routeRepository;

    @Autowired
    public CityService(CityRepository cityRepository, CommentRepository commentRepository, RouteRepository routeRepository) {
        this.cityRepository = cityRepository;
        this.commentRepository = commentRepository;
        this.routeRepository = routeRepository;
    }

    public void addCity(CityBean cityBean) {
        City newCity = new City();
        newCity.setCountry(cityBean.getCountry());
        newCity.setDescription(cityBean.getDescription());
        newCity.setName(cityBean.getName());
        Optional<City> city = cityRepository.findByName(newCity.getName());
        if (city.isPresent()) {
            return;
        } else {
            this.cityRepository.save(newCity);
        }
    }

    public Optional<CityWithCommentsBean> cityWithLimitedComments(String cityName, Integer commentsLimit) {

        CityWithCommentsBean cityWithCommentsBean = new CityWithCommentsBean();
        City city = cityRepository.findByName(cityName).get();
        if (commentsLimit > city.getComments().size()) {
            cityWithCommentsBean.comments = city.getComments().subList(0, city.getComments().size());
        } else {
            cityWithCommentsBean.comments = city.getComments().subList(0, commentsLimit);
        }
        cityWithCommentsBean.name = city.getName();
        cityWithCommentsBean.country = city.getCountry();
        cityWithCommentsBean.description = city.getDescription();

        return Optional.of(cityWithCommentsBean);
    }

    public Optional<List<CityWithCommentsBean>> allCitiesWithLimitedComments(Integer commentsLimit) {
        List<CityWithCommentsBean> citiesWithCommentsBeans = new ArrayList<>();
        cityRepository.findAll().forEach(c -> {
            CityWithCommentsBean cityWithCommentsBean = new CityWithCommentsBean();
            if (commentsLimit > c.getComments().size()) {
                cityWithCommentsBean.comments = c.getComments().subList(0, c.getComments().size());
            } else {
                cityWithCommentsBean.comments = c.getComments().subList(0, commentsLimit);
            }
            cityWithCommentsBean.name = c.getName();
            cityWithCommentsBean.country = c.getCountry();
            cityWithCommentsBean.description = c.getDescription();
            citiesWithCommentsBeans.add(cityWithCommentsBean);
        });
        return Optional.of(citiesWithCommentsBeans);
    }

    public void addCommentForCity(CommentBean commentBean) {
        Comment comment = new Comment();
        Integer cityId = Integer.parseInt(commentBean.getCityId());
        City city = cityRepository.findById(cityId).orElseThrow(() -> new FlightAdvisorRuntimeException("We can't find a city"));
        comment.setBody(commentBean.getBody());
        comment.setCity(city);
        comment.setCreatedAt(LocalDate.now());
        comment.setUpdatedAt(LocalDate.now());
        commentRepository.save(comment);
    }

    public void deleteCommentForCity(String inputCityId, String inputCommentId) {
        Integer cityId = Integer.parseInt(inputCityId);
        Integer commentId = Integer.parseInt(inputCommentId);
        City city = cityRepository.findById(cityId).orElseThrow(() -> new FlightAdvisorRuntimeException("We can't find a city"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new FlightAdvisorRuntimeException("We can't find a comment"));
        commentRepository.delete(comment);
    }

    /**
     * Better way to update comment - with mapping
     *
     * @param inputCityId
     * @param inputCommentId
     * @param commentBean
     */
    public void updateCommentForCity(String inputCityId, String inputCommentId, CommentBean commentBean) {

        Integer cityId = Integer.parseInt(inputCityId);
        City city = cityRepository.findById(cityId).get();
        Integer commentId = Integer.parseInt(inputCommentId);
        commentRepository.findOneByCityId(city, commentId)
                .map(comment -> {
                    comment.setBody(commentBean.getBody());
                    comment.setUpdatedAt(LocalDate.now());
                    return commentRepository.save(comment);
                }).orElseThrow(() -> new FlightAdvisorRuntimeException("We can't find a comment"));
    }

}
