package com.miro.flightadvisor.security.controllers;

import com.miro.flightadvisor.security.beans.Login;
import com.miro.flightadvisor.security.entities.User;
import com.miro.flightadvisor.security.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public String login(@RequestBody @Valid Login login) {
       return userService.signin(login.getUsername(), login.getPassword()).orElseThrow(()->
               new HttpServerErrorException(HttpStatus.FORBIDDEN, "Login Failed"));
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public User signup(@RequestBody @Valid Login login){
        return userService.signup(login.getUsername(), login.getPassword(), login.getFirstName(),
                login.getLastName()).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST,"User already exists"));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

}