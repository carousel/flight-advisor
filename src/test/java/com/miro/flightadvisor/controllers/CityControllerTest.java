package com.miro.flightadvisor.controllers;

import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.security.beans.LoginBean;
import com.miro.flightadvisor.security.beans.SignupBean;
import com.miro.flightadvisor.security.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplateExtensionsKt;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CityControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;
    
    @Test
    void comments() {
        //first signup
        SignupBean signupBean = new SignupBean();
        signupBean.setFirstName("Jimi");
        signupBean.setLastName("Hendrix");
        signupBean.setPassword("fpwe8fpoiwdfl");
        String password = signupBean.getPassword();
        signupBean.setUsername("guitargod");
        String username = signupBean.getUsername();
        HttpEntity<SignupBean> signup = new HttpEntity<>(signupBean);
        User user = testRestTemplate.postForObject("/users/signup", signup, User.class);

        //then signin with credentials
        //get jwt bearer authorization in response
        LoginBean loginBean = new LoginBean(username, password);
        HttpEntity<LoginBean> signin = new HttpEntity<>(loginBean);
        String token = testRestTemplate.postForObject("/users/signin", signin, String.class);

        //send request for resource with jwt token
        HttpHeaders httpHeaders = new HttpHeaders();
        String bearer = "Bearer " + token;
        httpHeaders.add("Authorization", bearer);
        HttpEntity headers = new HttpEntity<>(httpHeaders);
        String url = "/cities/Amsterdam";
        ResponseEntity<City> city = testRestTemplate.exchange(url, HttpMethod.GET, headers, City.class, 1);
        String country = city.getBody().getCountry();

        //test
        assertEquals(country, "Netherlands");

    }
}