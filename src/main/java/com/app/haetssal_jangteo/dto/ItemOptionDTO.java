package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.domain.ItemOptionVO;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ItemOptionDTO {
    private Long id;
    private Long optionItemId;
    private String optionName;
    private String optionDetail;
    private String optionPrice;
    private String optionStock;

    public ItemOptionVO toVO() {
        return ItemOptionVO.builder()
                .id(id)
                .optionItemId(optionItemId)
                .optionName(optionName)
                .optionDetail(optionDetail)
                .optionPrice(optionPrice)
                .optionStock(optionStock)
                .build();
    }


}
