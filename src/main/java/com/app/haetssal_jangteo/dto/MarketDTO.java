package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.enumeration.State;
import com.app.haetssal_jangteo.domain.MarketVO;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class MarketDTO {
    private Long id;
    private String marketRegion;
    private String marketName;
    private String marketLocation;
    private State marketState;
    private String createdDatetime;
    private String updatedDatetime;

    public MarketVO toVO() {
        return MarketVO.builder()
                .id(id)
                .marketRegion(marketRegion)
                .marketName(marketName)
                .marketLocation(marketLocation)
                .marketState(marketState)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
