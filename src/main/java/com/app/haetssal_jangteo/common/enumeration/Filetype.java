package com.app.haetssal_jangteo.common.enumeration;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Filetype {
    IMAGE("image"), DOCUMENT("document");

    private String value;

    private static final Map<String, Filetype> TYPE_MAP =
            Arrays.stream(Filetype.values()).collect(Collectors.toMap(Filetype::getValue, Function.identity()));

    Filetype(String value) {
        this.value = value;
    }

    public static Filetype getFiletype(String value) {
        return TYPE_MAP.get(value);
    }

    public String getValue() {
        return value;
    }
}
