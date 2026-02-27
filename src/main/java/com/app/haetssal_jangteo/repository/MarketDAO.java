package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.domain.MarketVO;
import com.app.haetssal_jangteo.mapper.MarketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MarketDAO {
    private final MarketMapper marketMapper;

    // 지역으로 마켓들 조회
    public List<MarketVO> findByRegion(String marketRegion) {
        return marketMapper.selectByRegion(marketRegion);
    }
}
