package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.enumeration.FileItemType;
import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.domain.FileItemVO;
import com.app.haetssal_jangteo.domain.FileMarketVO;
import com.app.haetssal_jangteo.domain.FileVO;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class FileMarketDTO {
    private Long id;
    private Filetype fileType;
    private String fileName;
    private String fileOriginName;
    private String fileSavedPath;
    private String fileSize;
    private String createdDatetime;

    private Long marketId;

    public FileVO toFileVO() {
        return FileVO.builder()
                .id(id)
                .fileType(fileType)
                .fileName(fileName)
                .fileOriginName(fileOriginName)
                .fileSavedPath(fileSavedPath)
                .fileSize(fileSize)
                .createdDatetime(createdDatetime)
                .build();
    }

    public FileMarketVO toFileMarketVO() {
        return FileMarketVO.builder().id(id).marketId(marketId).build();
    }
}
