package com.app.haetssal_jangteo.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ItemDescImageDTO {
    private Long id;

    // 상품 설명 이미지 (내용, 판매자, 환불/교환)
    private List<FileItemDTO> itemDescImages = new ArrayList<>();
    private List<FileItemDTO> itemSellerImages = new ArrayList<>();
    private List<FileItemDTO> itemRefundImages = new ArrayList<>();
}
