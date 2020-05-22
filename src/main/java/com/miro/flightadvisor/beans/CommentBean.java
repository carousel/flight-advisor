package com.miro.flightadvisor.beans;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentBean {
    @NotNull
    private String body;
    @NotNull
    private String cityId;

    public CommentBean(@NotNull String body, @NotNull String cityId) {
        this.body = body;
        this.cityId = cityId;
    }
}
