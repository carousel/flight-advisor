package com.miro.flightadvisor.security.repositories;

import com.miro.flightadvisor.security.entities.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthGroupRepository extends JpaRepository<AuthGroup, Long> {
    List<AuthGroup> findByUsername(String username);
}
