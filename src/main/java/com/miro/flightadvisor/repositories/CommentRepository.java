package com.miro.flightadvisor.repositories;

import com.miro.flightadvisor.entities.City;
import com.miro.flightadvisor.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("select c from Comment c where c.city=:#{#city} and c.id=:commentId")
    Optional<Comment> findOneByCityId(City city, Integer commentId);
}
