package com.miro.flightadvisor.security.controllers;

import com.miro.flightadvisor.security.beans.LoginBean;
import com.miro.flightadvisor.security.beans.SignupBean;
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
    public String login(@RequestBody @Valid LoginBean loginBean) {
        return userService.signin(loginBean.getUsername(), loginBean.getPassword()).orElseThrow(() ->
                new HttpServerErrorException(HttpStatus.FORBIDDEN, "login failed, please try again"));
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public User signup(@Valid @RequestBody SignupBean signupBean) {
        return userService.signup(signupBean.getUsername(), signupBean.getPassword(), signupBean.getFirstName(),
                signupBean.getLastName()).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User already exists"));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

}