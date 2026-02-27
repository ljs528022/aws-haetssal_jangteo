//package com.app.haetssal_jangteo.service;
//
//import com.app.haetssal_jangteo.domain.MarketVO;
//import com.app.haetssal_jangteo.dto.MarketDTO;
//import com.app.haetssal_jangteo.repository.MarketDAO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@SpringBootTest
//@Slf4j
//public class MarketServiceTests {
//
//    @Autowired
//    private MarketDAO marketDAO;
//
//    @Test
//    public void testFindByRegion() {
//        String region = "서울";
//        List<MarketDTO> foundMarkets = marketDAO.findByRegion(region)
//                .stream().map(marketVO -> toDTO(marketVO)).collect(Collectors.toList());
//
//        log.info("{}..........", foundMarkets);
//    }
//
//
//    // toDTO
//    public MarketDTO toDTO(MarketVO marketVO) {
//        MarketDTO marketDTO = new MarketDTO();
//        marketDTO.setId(marketVO.getId());
//        marketDTO.setMarketRegion(marketVO.getMarketRegion());
//        marketDTO.setMarketName(marketVO.getMarketName());
//        marketDTO.setMarketLocation(marketVO.getMarketLocation());
//        marketDTO.setMarketState(marketVO.getMarketState());
//        marketDTO.setCreatedDatetime(marketVO.getCreatedDatetime());
//        marketDTO.setUpdatedDatetime(marketVO.getUpdatedDatetime());
//
//        return marketDTO;
//    }
//}
