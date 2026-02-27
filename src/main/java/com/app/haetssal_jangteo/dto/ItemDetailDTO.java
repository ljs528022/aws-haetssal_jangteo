package com.app.haetssal_jangteo.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ItemDetailDTO {
    private Long id;

    // 상품 기본 정보
    private Long itemCategoryId;
    private String itemCategoryName;
    private Long itemSubCategoryId;
    private String itemSubCategoryName;
    private String itemName;
    private String itemPrice;
    private String itemStock;
    private String itemDeliveryFee;
    private String itemContent;
    private String itemViewCount;

    // 상품 썸네일 & 옵션들
    private List<FileItemDTO> itemThumbnails = new ArrayList<>();
    private List<ItemOptionDTO> itemOptions = new ArrayList<>();

    // 상품 가게 정보
    private Long storeId;
    private Long storeOwnerId;
    private String storeName;
    private String storeScore;

    // 같은 가게 상품 개수
    private int storeItemCount;
    // 같은 가게 판매 한 상품 개수
    private int storeSoldCount;

    // 가게 소유주 마지막 로그인 시간
    private String ownerLatestLogin;

    // 가게 프로필 사진
    private String storeProfilePath;
    private String storeProfileName;
    private String storeProfileOriginName;

    // 같은 카테고리 상품들
    private List<ItemDTO> sameCategoryItems = new ArrayList<>();
}
