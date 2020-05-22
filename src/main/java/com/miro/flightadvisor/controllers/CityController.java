package com.miro.flightadvisor.controllers;

import com.miro.flightadvisor.beans.CityBean;
import com.miro.flightadvisor.beans.CommentBean;
import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.entities.Comment;
import com.miro.flightadvisor.services.CityService;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public Optional<List<City>> cities() {
        return cityService.allCities();
    }

    @GetMapping("/comments")
    public Optional<List<Comment>> comments() {
        return cityService.allCommentsForCity();
    }

    @PostMapping("/cities/{cityId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CommentBean> addCommentForCity(@PathVariable("cityId") String cityId, @RequestBody @Valid CommentBean commentBean) {
        commentBean.setCityId(cityId);
        cityService.addCommentForCity(commentBean);
        return ResponseEntity.ok(commentBean);
    }
    @DeleteMapping("/cities/{cityId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> deleteCommentForCity( @PathVariable("cityId") String cityId, @PathVariable("commentId") String commentId) {
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
