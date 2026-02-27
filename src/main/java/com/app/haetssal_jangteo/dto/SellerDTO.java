package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.enumeration.SellerState;
import com.app.haetssal_jangteo.domain.SellerVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class SellerDTO {
    private Long id;
    private String sellerBankName;
    private String sellerDepositor;
    private String sellerAccountNumber;
    private SellerState sellerState;

    public SellerVO toSellerVO() {
        return SellerVO.builder()
                .id(id)
                .sellerBankName(sellerBankName)
                .sellerDepositor(sellerDepositor)
                .sellerAccountNumber(sellerAccountNumber)
                .sellerState(sellerState)
                .build();
    }
}
