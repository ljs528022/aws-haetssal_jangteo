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
    private String createdDatetime;
    //    상품이랑 가게랑 장터-목록뿌릴때
    private String itemName;
    private String itemPrice;
    private String itemContent;
    //    결제화면에서 이미지 필요 + 뿌릴때도 이미지 필요
    private String fileName;
    private String fileSavedPath;

    private Long storeOwnerId;
    private String storeName;

    private String marketName;

    private String userName;
    private String userPhone;
    private String userEmail;
    private String itemDeliveryFee;
    private String itemTotalPrice;

    public PaymentVO toPaymentVO() {
        return PaymentVO.builder()
                .id(id)
                .userId(userId)
                .itemId(itemId)
                .paymentState(paymentState)
                .build();
    }
}