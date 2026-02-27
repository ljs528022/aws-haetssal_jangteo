package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.MarketVO;
import com.app.haetssal_jangteo.dto.MarketDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class MarketMapperTests {

    @Autowired
    private MarketMapper marketMapper;

    @Test
    public void testInsert() {
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.setMarketRegion("서울");
        marketDTO.setMarketName("market name1");
        marketDTO.setMarketLocation("seoul gosanjaro 10 gil");

        MarketDTO marketDTO2 = new MarketDTO();
        marketDTO2.setMarketRegion("서울");
        marketDTO2.setMarketName("market name2");
        marketDTO2.setMarketLocation("seoul gosanjaro 11 gil");

        MarketDTO marketDTO3 = new MarketDTO();
        marketDTO3.setMarketRegion("경기");
        marketDTO3.setMarketName("market name3");
        marketDTO3.setMarketLocation("seoul gosanjaro 12 gil");

        marketMapper.insert(marketDTO);
        marketMapper.insert(marketDTO2);
        marketMapper.insert(marketDTO3);
    }

    @Test
    public void testSelectAll() {
        List<MarketVO> marketList = marketMapper.selectAll();
        log.info("{}.....", marketList);
    }

    @Test
    public void testSelectByRegion() {
        String marketRegion = "서울";
        List<MarketVO> foundMarkets = marketMapper.selectByRegion(marketRegion);
        log.info("{}.......", foundMarkets);
    }

    @Test
    public void testSelectById() {
        Optional<MarketVO> foundMarket = marketMapper.selectById(3L);
        log.info("{}........", foundMarket);
    }

}
