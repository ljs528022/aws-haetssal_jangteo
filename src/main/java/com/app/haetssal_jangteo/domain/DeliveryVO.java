package com.app.haetssal_jangteo.domain;

import com.app.haetssal_jangteo.audit.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class DeliveryVO extends Period {
    private Long id;
    private Long userId;
    private String deliveryName;
    private String deliveryAddress;
    private String deliveryDetailAddress;
    private String deliveryReceiver;
    private String receiverPhone;
}
