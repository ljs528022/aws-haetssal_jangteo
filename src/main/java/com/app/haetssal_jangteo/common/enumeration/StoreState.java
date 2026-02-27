package com.app.haetssal_jangteo.common.enumeration;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum StoreState {
    PENDING("pending"), DENIED("denied"), OPEN("open"), CLOSE("close");

    private String value;

    private static final Map<String, StoreState> STATE_MAP =
            Arrays.stream(StoreState.values()).collect(Collectors.toMap(StoreState::getValue, Function.identity()));

    StoreState(String value) {
        this.value = value;
    }

    public static StoreState getStoreState(String value) {
        return STATE_MAP.get(value);
    }

    public String getValue() {
        return value;
    }
}
