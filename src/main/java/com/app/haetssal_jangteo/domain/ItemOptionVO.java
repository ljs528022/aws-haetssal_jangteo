package com.app.haetssal_jangteo.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class ItemOptionVO {
    private Long id;
    private Long optionItemId;
    private String optionName;
    private String optionDetail;
    private String optionPrice;
    private String optionStock;
}
