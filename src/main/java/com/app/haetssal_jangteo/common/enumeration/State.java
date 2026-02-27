package com.app.haetssal_jangteo.common.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum State {
    ACTIVE("active"), INACTIVE("inactive"), PENDING("pending");

    private String value;

    private static final Map<String, State> STATE_MAP =
            Arrays.stream(State.values()).collect(Collectors.toMap(State::getValue, Function.identity()));

    State(String value) {
        this.value = value;
    }

    @JsonCreator
    public static State getState(String value) {
        return STATE_MAP.get(value);
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}