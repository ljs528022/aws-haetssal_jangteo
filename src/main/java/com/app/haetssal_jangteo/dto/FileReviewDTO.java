package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.domain.FileReviewVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class FileReviewDTO {
    private Long fileId;
    private Long reviewId;

    // 파일 정보 (이미지 보여줄 때)
    private String fileName;
    private String fileSavedPath;

    public FileReviewVO toFileReviewVO() {
        return FileReviewVO.builder()
                .fileId(fileId)
                .reviewId(reviewId)
                .build();
    }
}
