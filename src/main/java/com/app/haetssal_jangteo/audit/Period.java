package com.app.haetssal_jangteo.audit;

import lombok.experimental.SuperBuilder;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public abstract class Period {
    private String createdDatetime;
    private String updatedDatetime;
}
