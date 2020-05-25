package com.miro.flightadvisor.beans;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
/**
 * Main bean (DTO) for transferring data when making CRUD operations of comments
 */
public class CommentBean {
    @NotNull
    private String body;
    @NotNull
    private String cityId;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public CommentBean(@NotNull String body, @NotNull String cityId) {
        this.body = body;
        this.cityId = cityId;
    }
}
