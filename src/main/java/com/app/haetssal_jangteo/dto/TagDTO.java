package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.domain.TagVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class TagDTO {

    private Long id;
    private String tagName;
    private Long itemId;

    public FileDTO toVO() {
        return TagVO.builder()
                .id(id)
                .tagName(tagName)
                .itemId(itemId)
                .build();
    }
}













