package com.app.haetssal_jangteo.domain;

import com.app.haetssal_jangteo.common.enumeration.FileItemType;
import lombok.*;

@Getter @ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FileItemVO {
    private Long id;
    private Long itemId;
    private FileItemType fileItemType;
}
