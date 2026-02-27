package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.domain.CartItemVO;
import com.app.haetssal_jangteo.domain.CartVO;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class CartItemDTO {
    private Long id;
    private Long cartId;
    private Long userId;
    private Long itemId;
    private String itemName;
    private String itemOption;
    private String itemPrice;
    private int itemCount;
    private String itemDeliveryFee;

    // 카테고리 이름
    private String categoryName;
    private String subCategoryName;

    // 가게 이름
    private String storeName;

    // 해당 상품의 썸네일
    private String fileName;
    private String fileSavedPath;

    // 장바구니에 담을 옵션 id 배열
    private String[] optionsToAdd;

    public CartVO toVO() {
        return CartVO.builder()
                .id(cartId)
                .userId(userId)
                .build();
    }

    public CartItemVO toCartItemVO() {
        return CartItemVO.builder()
                .id(id)
                .cartId(cartId)
                .itemId(itemId)
                .itemName(itemName)
                .itemOption(itemOption)
                .itemPrice(itemPrice)
                .build();
    }
}
