package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.domain.CartVO;
import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class CartDTO {
    private Long id;
    private Long userId;

    public CartVO toVO() {
        return CartVO.builder()
                .id(id)
                .userId(userId)
                .build();
    }
}
