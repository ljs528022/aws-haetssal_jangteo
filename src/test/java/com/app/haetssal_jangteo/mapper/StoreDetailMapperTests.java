package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.dto.StoreDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class StoreDetailMapperTests {

    @Autowired
    private StoreDetailMapper storeDetailMapper;

    @Test
    public void testSelectById() {
        Optional<StoreDetailDTO> foundStoreInfo = storeDetailMapper.selectById(8L);

        log.info("{}....", foundStoreInfo);
    }
}
