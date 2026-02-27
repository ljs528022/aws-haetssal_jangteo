package com.app.haetssal_jangteo.common.enumeration;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SellerState {
    PENDING("pending"), APPROVED("approved"), DENIED("denied");

    private String value;

    private static final Map<String, SellerState> STATE_MAP =
            Arrays.stream(SellerState.values()).collect(Collectors.toMap(SellerState::getValue, Function.identity()));

    SellerState(String value) {
        this.value = value;
    }

    public static SellerState getSellerState(String value) {
        return STATE_MAP.get(value);
    }

    public String getValue() {
        return value;
    }
}
