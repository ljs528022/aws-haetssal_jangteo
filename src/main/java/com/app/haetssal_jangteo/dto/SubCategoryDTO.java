package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.domain.SubCategoryVO;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class SubCategoryDTO {
    private Long id;
    private String categoryName;
    private Long parentCategoryId;

    public SubCategoryVO toVO() {
        return SubCategoryVO.builder()
                .id(id)
                .categoryName(categoryName)
                .parentCategoryId(parentCategoryId)
                .build();
    }
}
