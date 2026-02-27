package com.app.haetssal_jangteo.domain;

import com.app.haetssal_jangteo.dto.FileDTO;
import lombok.*;
@Getter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class TagVO extends FileDTO {
    private Long id;
    private String tagName;
    private Long itemId;
}













