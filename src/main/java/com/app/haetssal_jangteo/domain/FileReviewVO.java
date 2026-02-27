package com.app.haetssal_jangteo.domain;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FileReviewVO {
    private Long fileId;
    private Long reviewId;
}
