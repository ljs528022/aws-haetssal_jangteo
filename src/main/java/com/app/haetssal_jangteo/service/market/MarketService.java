package com.app.haetssal_jangteo.service.market;

import com.app.haetssal_jangteo.domain.MarketVO;
import com.app.haetssal_jangteo.dto.MarketDTO;
import com.app.haetssal_jangteo.repository.MarketDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MarketService {
    private final MarketDAO marketDAO;

    // 지역으로 마켓 조회
    public List<MarketDTO> findByRegion(String marketRegion) {
        return marketDAO.findByRegion(marketRegion)
                .stream().map(marketVO -> toDTO(marketVO)).collect(Collectors.toList());
    }

    // toDTO
    public MarketDTO toDTO(MarketVO marketVO) {
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.setId(marketVO.getId());
        marketDTO.setMarketRegion(marketVO.getMarketRegion());
        marketDTO.setMarketName(marketVO.getMarketName());
        marketDTO.setMarketLocation(marketVO.getMarketLocation());
        marketDTO.setMarketState(marketVO.getMarketState());
        marketDTO.setCreatedDatetime(marketVO.getCreatedDatetime());
        marketDTO.setUpdatedDatetime(marketVO.getUpdatedDatetime());

        return marketDTO;
    }
}
