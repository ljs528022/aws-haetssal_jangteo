package com.app.haetssal_jangteo.domain;

import com.app.haetssal_jangteo.audit.Period;
import com.app.haetssal_jangteo.common.enumeration.State;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @ToString(callSuper = true)
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class ItemVO extends Period {
    private Long id;
    private Long itemStoreId;
    private Long itemCategoryId;
    private Long itemSubCategoryId;
    private String itemName;
    private String itemType;
    private String itemStock;
    private String itemPrice;
    private String itemDeliveryFee;
    private String itemContent;
    private State itemState;
    private int itemViewCount;
}
