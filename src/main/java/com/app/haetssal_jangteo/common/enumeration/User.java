package com.app.haetssal_jangteo.common.enumeration;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum User {
    NORMAL("normal"), SELLER("seller") ,ADMIN("admin");

    private String value;

    private static final Map<String, User> USER_MAP =
            Arrays.stream(User.values()).collect(Collectors.toMap(User::getValue, Function.identity()));

    User(String value) {
        this.value = value;
    }

    public static User getUser(String value) {
        return USER_MAP.get(value);
    }

    public String getValue() {
        return value;
    }
}
