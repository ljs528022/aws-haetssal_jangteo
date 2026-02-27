package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.enumeration.StoreState;
import com.app.haetssal_jangteo.domain.StoreVO;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class StoreDTO {
    private Long id;
    private Long storeMarketId;
    private Long storeOwnerId;
    private Long storeCategoryId;
    private String storeCategoryName;
    private String storeName;
    private String storeIntro;
    private String storeAddress;
    private String storeScore;
    private StoreState storeState;
    private boolean storeIsConfirmed;
    private String createdDatetime;
    private String updatedDatetime;

    // 가게 프로필 이미지
    private String fileName;
    private String fileOriginName;
    private String fileSavedPath;

    // 삭제할 상점 이미지 id
    private String toDeleteFileId;

    public StoreVO toVO() {
        return StoreVO.builder()
                .id(id)
                .storeMarketId(storeMarketId)
                .storeOwnerId(storeOwnerId)
                .storeCategoryId(storeCategoryId)
                .storeName(storeName)
                .storeIntro(storeIntro)
                .storeAddress(storeAddress)
                .storeScore(storeScore)
                .storeState(storeState)
                .storeIsConfirmed(storeIsConfirmed)
                .build();
    }
}
