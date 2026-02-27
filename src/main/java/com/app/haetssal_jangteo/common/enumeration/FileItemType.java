package com.app.haetssal_jangteo.common.enumeration;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum FileItemType {
    THUMBNAIL("thumbnail"), DESC("desc"), SELLER_INFO("seller-info"), REFUND("refund") ;

    private String value;

    private static final Map<String, FileItemType> TYPE_MAP =
            Arrays.stream(FileItemType.values()).collect(Collectors.toMap(FileItemType::getValue, Function.identity()));

    FileItemType(String value) {
        this.value = value;
    }

    public static FileItemType getFiletype(String value) {
        return TYPE_MAP.get(value);
    }

    public String getValue() {
        return value;
    }
}
