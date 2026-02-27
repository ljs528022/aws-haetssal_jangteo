package com.app.haetssal_jangteo.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class KakaoLoginException extends RuntimeException {
    public KakaoLoginException(String message) {
        super(message);
    }
}
