package com.app.haetssal_jangteo.domain;

import com.app.haetssal_jangteo.audit.Period;
import com.app.haetssal_jangteo.common.enumeration.StoreState;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class StoreVO extends Period {
    private Long id;
    private Long storeMarketId;
    private Long storeOwnerId;
    private Long storeCategoryId;
    private String storeName;
    private String storeIntro;
    private String storeAddress;
    private String storeScore;
    private StoreState storeState;
    private boolean storeIsConfirmed;
}
