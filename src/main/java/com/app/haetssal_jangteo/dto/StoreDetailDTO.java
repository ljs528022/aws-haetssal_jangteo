package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.enumeration.StoreState;
import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class StoreDetailDTO {
    private Long id;

    // 가게 기본 정보
    private Long storeMarketId;
    private Long storeOwnerId;
    private Long storeCategoryId;
    private String storeCategoryName;
    private String storeName;
    private String storeIntro;
    private String storeAddress;
    private String storeScore;

    // 가게 소유주 id, 마지막 로그인 시간
    private String ownerLatestLogin;

    // 가게 프로필 정보
    private Long fileId;
    private String fileName;
    private String fileOriginName;
    private String fileSavedPath;

    // 판매 상품 개수 + 후기 개수
    private int itemCount;
    private int reviewCount;

    // 최근 등록된 상품 10개
    private List<ItemDTO> storeItems;

    // 가게의 상품의 후기들
    private List<ReviewDTO> storeReviews;

}
