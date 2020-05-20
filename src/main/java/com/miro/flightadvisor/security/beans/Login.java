package com.miro.flightadvisor.security.beans;

import javax.validation.constraints.NotNull;

/**
 * Created By Mary Ellen Bowman
 */
public class Login {
    @NotNull
    private String username;

    @NotNull
    private String password;

    private String firstName;

    private String lastName;

    /**
     * Default constructor
     */
    protected Login() {
    }

    /**
     * Partial constructor
     * @param username
     * @param password
     */
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    /**
     * Full constructor
     * @param username
     * @param password
     */
    public Login(String username, String password, String firstName, String lastName) {
        this(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
