package com.github.chMatvey.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DroneModel {
    LIGHTWEIGHT("Lightweight"),
    MIDDLEWEIGHT("Middleweight"),
    CRUISERWEIGHT("Cruiserweight"),
    HEAVYWEIGHT("Heavyweight");

    @JsonValue
    private final String value;
}
