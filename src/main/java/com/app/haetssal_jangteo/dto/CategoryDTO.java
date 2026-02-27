package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.domain.CategoryVO;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String categoryName;

    public CategoryVO toVO() {
        return CategoryVO.builder()
                .id(id)
                .categoryName(categoryName)
                .build();
    }
}
