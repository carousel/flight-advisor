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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Api(description = "Api for managing cities")
@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CityController.class);


    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping("")
    @ApiOperation(value = "return city with comments")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Server error")})
    public Optional<?> cities(@RequestParam(value = "cq") Optional<Integer> cq) {
        if (cq.isPresent()) {
            return cityService.allCitiesWithLimitedComments(cq.get());
        } else {
            return cityService.allCities();
        }
    }

    @GetMapping("{cityName}")
    @ApiOperation(value = "fetch city by city name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Server error")})
    @PreAuthorize("hasRole('ROLE_USER')")
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


    @GetMapping("comments")
    @ApiOperation(value = "fetch all comments")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Server error")})
    @PreAuthorize("hasRole('ROLE_USER')")
    public Optional<List<Comment>> comments() {
        return cityService.allCommentsForCity();
    }

    @PostMapping("{cityId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "add new comment for city")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Server error")})
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> addCommentForCity(@PathVariable("cityId") String cityId, @RequestBody @Valid CommentBean commentBean) {
        LOGGER.debug(String.format("Adding commentt for city %s", LocalDate.now()));
        commentBean.setCityId(cityId);
        cityService.addCommentForCity(commentBean);
        return ResponseEntity.ok("Comment created");
    }

    @DeleteMapping("{cityId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "delete comment for city")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Server error")})
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> deleteCommentForCity(@PathVariable("cityId") String cityId, @PathVariable("commentId") String commentId) {
        LOGGER.debug(String.format("Removing comment from city %s", LocalDate.now()));
        cityService.deleteCommentForCity(cityId, commentId);
        return ResponseEntity.ok("Comment deleted");
    }

    @PutMapping("{cityId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "update comment for city")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Server error")})
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> updateCommentForCity(

            @PathVariable("cityId") String cityId,
            @PathVariable("commentId") String commentId,
            @RequestBody @Valid CommentBean commentBean) {
        LOGGER.debug(String.format("Updating commentt for city %s", LocalDate.now()));
        cityService.updateCommentForCity(cityId, commentId, commentBean);
        return ResponseEntity.ok("Comment updated");
    }

}
