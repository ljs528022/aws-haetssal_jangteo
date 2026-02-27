package com.app.haetssal_jangteo.common.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class StoreSearch {
    private String region;  // 지역 이름
    private Long categoryId; // 카테고리 id
    private Long marketId; // 장터 id
    private String state; // 가게 상태
    private String orderValue; // 정렬 값
}
