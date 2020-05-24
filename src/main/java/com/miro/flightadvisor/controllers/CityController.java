package com.miro.flightadvisor.controllers;

import com.miro.flightadvisor.beans.CityWithCommentsBean;
import com.miro.flightadvisor.beans.CommentBean;
import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.entities.Comment;
import com.miro.flightadvisor.entities.Route;
import com.miro.flightadvisor.repositories.CityRepository;
import com.miro.flightadvisor.repositories.CommentRepository;
import com.miro.flightadvisor.services.CityService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class CityController {

    private CityService cityService;


    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping("/cities")
    public Optional<?> cities(@RequestParam(value = "cq") Optional<Integer> cq) {
        if (cq.isPresent()) {
            return cityService.allCitiesWithLimitedComments(cq.get());
        } else {
            return cityService.allCities();
        }
    }

    @GetMapping("/cities/{cityName}")
    public Optional<?> getCity(
            @NotNull @PathVariable(value = "cityName") String cityName,
            @RequestParam(value = "cq") Optional<Integer> cq
    ) {
        if (cq.isPresent()) {
            return cityService.cityWithLimitedComments(cityName, cq.get());
        } else {
            return cityService.city(cityName);
        }
    }


    @GetMapping("/comments")
    public Optional<List<Comment>> comments() {
        return cityService.allCommentsForCity();
    }

    @PostMapping("/cities/{cityId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addCommentForCity(@PathVariable("cityId") String cityId, @RequestBody @Valid CommentBean commentBean) {
        commentBean.setCityId(cityId);
        cityService.addCommentForCity(commentBean);
        return ResponseEntity.ok("Comment created");
    }

    @DeleteMapping("/cities/{cityId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> deleteCommentForCity(@PathVariable("cityId") String cityId, @PathVariable("commentId") String commentId) {
        cityService.deleteCommentForCity(cityId, commentId);
        return ResponseEntity.ok("Comment deleted");
    }

    @PutMapping("/cities/{cityId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> updateCommentForCity(
            @PathVariable("cityId") String cityId,
            @PathVariable("commentId") String commentId,
            @RequestBody @Valid CommentBean commentBean) {
        cityService.updateCommentForCity(cityId, commentId, commentBean);
        return ResponseEntity.ok("Comment updated");
    }

}
