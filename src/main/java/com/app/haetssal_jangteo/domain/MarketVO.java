package com.app.haetssal_jangteo.domain;

import com.app.haetssal_jangteo.audit.Period;
import com.app.haetssal_jangteo.common.enumeration.State;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @ToString(callSuper = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class MarketVO extends Period {
    private Long id;
    private String marketRegion;
    private String marketName;
    private String marketLocation;
    private State marketState;
}
