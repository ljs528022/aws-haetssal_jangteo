package com.app.haetssal_jangteo.common.enumeration;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PaymentState {
    PENDING("pending"), SHIPPING("shipping"), COMPLETE("complete"), CANCELLED("cancelled");

    private String value;

    private static final Map<String, PaymentState> PAYMENT_STATE_MAP =
            Arrays.stream(PaymentState.values()).collect(Collectors.toMap(PaymentState::getValue, Function.identity()));

    PaymentState(String value) {
        this.value = value;
    }

    public static PaymentState getPaymentState(String value) {
        return PAYMENT_STATE_MAP.get(value);
    }

    public String getValue() {
        return value;
    }
}
