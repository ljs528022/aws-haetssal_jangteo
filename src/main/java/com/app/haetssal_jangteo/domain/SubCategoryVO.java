package com.app.haetssal_jangteo.domain;

import lombok.*;

@Getter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class SubCategoryVO {
    private Long id;
    private String categoryName;
    private Long parentCategoryId;
}
