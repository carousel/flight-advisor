package com.miro.flightadvisor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class FlightAdvisorServiceApplication {


    public static void main(String[] args) {

        SpringApplication.run(FlightAdvisorServiceApplication.class, args);

    }

    public static void jws() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String jws = Jwts.builder().setSubject("admin").signWith(key).compact();

        System.out.println(jws);
    }

}
