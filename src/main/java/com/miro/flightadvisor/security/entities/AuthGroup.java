package com.miro.flightadvisor.security.entities;

import javax.persistence.*;

@Entity
@Table(name = "AUTH_USER_GROUP")
public class AuthGroup {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthGroup() {
        return authGroup;
    }

    public void setAuthGroup(String authGroup) {
        this.authGroup = authGroup;
    }

    @Id
    @Column(name = "auth_user_group_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "username")
    private String username;

    @Column(name = "auth_group")
    private String authGroup;
}
