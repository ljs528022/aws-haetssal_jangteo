package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.enumeration.PaymentState;
import com.app.haetssal_jangteo.common.enumeration.State;
import com.app.haetssal_jangteo.domain.PaymentVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class PaymentDTO {
    private Long id;
    private Long userId;
    private Long itemId;
    private PaymentState paymentState;
//    상품이랑 가게랑 장터-목록뿔ㄹ때
    private String itemName;
    private String itemPrice;
    private String itemContent;

    private String storeName;

    private String marketName;

    public PaymentVO toPaymentVO() {
        return PaymentVO.builder()
                .id(id)
                .userId(userId)
                .itemId(itemId)
                .paymentState(paymentState)
                .build();
    }
}
