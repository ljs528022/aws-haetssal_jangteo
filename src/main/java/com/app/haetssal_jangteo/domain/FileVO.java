package com.app.haetssal_jangteo.domain;

import com.app.haetssal_jangteo.common.enumeration.Filetype;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter @ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FileVO {
    private Long id;
    private Filetype fileType;
    private String fileName;
    private String fileOriginName;
    private String fileSavedPath;
    private String fileSize;
    private String createdDatetime;
}
