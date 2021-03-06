package com.miro.flightadvisor.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;


/**
 * Security User Entity.
 */
@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    @NotBlank
    private String username;
    @Column(name = "password")
    @JsonIgnore
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "salt")
    @JsonIgnore
    private byte[] salt;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))


    private List<Role> roles;

    public User(String username, String password, Role role, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.roles = Arrays.asList(role);
        this.firstName = firstName;
        this.lastName = lastName;
        this.salt = generateSalt();
    }


    /**
     * Added salt for convenience
     * But based on this Stack Overflow thread https://stackoverflow.com/questions/6832445/how-can-bcrypt-have-built-in-salts Spring is internally auto generating salt
     *
     * @return
     */
    public byte[] generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[64];
        secureRandom.nextBytes(salt);
        return salt;
    }
}

