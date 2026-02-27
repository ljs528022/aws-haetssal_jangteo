package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.domain.DeliveryVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class DeliveryDTO {
    private Long id;
    private Long userId;
    private String deliveryName;
    private String deliveryAddress;
    private String deliveryDetailAddress;
    private String deliveryReceiver;
    private String receiverPhone;
    private String createdDatetime;
    private String updatedDatetime;

    public DeliveryVO toVO() {
        return DeliveryVO.builder()
                .id(id)
                .userId(userId)
                .deliveryName(deliveryName)
                .deliveryAddress(deliveryAddress)
                .deliveryDetailAddress(deliveryDetailAddress)
                .deliveryReceiver(deliveryReceiver)
                .receiverPhone(receiverPhone)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
