package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.enumeration.State;
import com.app.haetssal_jangteo.domain.ReviewVO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private Long reviewItemId;
    private Long reviewUserId;
    private int reviewScoreQuality;
    private int reviewScoreDelivery;
    private int reviewScoreKind;
    private String reviewContent;
    private State reviewState;
    private String createdDatetime;
    private String updatedDatetime;

    // 리뷰 이미지 목록
    private List<FileReviewDTO> reviewFiles = new ArrayList<>();

    // 상품 정보 (목록에서 보여줄 때)
    private String itemName;
    private String itemPrice;
    private String storeName;

    // 유저 정보 (리뷰 표시할 때)
    private String userName;

    public ReviewVO toVO() {
        return ReviewVO.builder()
                .id(id)
                .reviewItemId(reviewItemId)
                .reviewUserId(reviewUserId)
                .reviewScoreQuality(reviewScoreQuality)
                .reviewScoreDelivery(reviewScoreDelivery)
                .reviewScoreKind(reviewScoreKind)
                .reviewContent(reviewContent)
                .reviewState(reviewState)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
