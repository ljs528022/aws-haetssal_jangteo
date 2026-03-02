package com.app.haetssal_jangteo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class MypageCountDTO {
    private int reviewCount;
    private int pendingCount;
    private int completeCount;
}
