package com.miro.flightadvisor.security.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@Getter
@Setter
public class SignupBean {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private String salt;
}
