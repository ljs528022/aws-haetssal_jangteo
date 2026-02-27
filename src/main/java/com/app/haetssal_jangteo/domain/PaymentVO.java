package com.app.haetssal_jangteo.domain;

import com.app.haetssal_jangteo.common.enumeration.PaymentState;
import lombok.*;

@Getter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PaymentVO {
    private Long id;
    private Long userId;
    private Long itemId;
    private PaymentState paymentState;
}
