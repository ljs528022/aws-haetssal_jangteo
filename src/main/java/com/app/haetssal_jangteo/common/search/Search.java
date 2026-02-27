package com.app.haetssal_jangteo.common.search;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Search {
    private String keyword;
    private String type;

    // 상품 필터
    private String categoryId;
    private String itemState;

    // 가게 필터
    private String marketRegion;
    private String marketState;
}
