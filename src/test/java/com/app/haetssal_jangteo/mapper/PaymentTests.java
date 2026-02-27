package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.dto.PaymentDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class PaymentTests {
    @Autowired
    private PaymentMapper paymentMapper;

    @Test
    public void selectCompleteItemsByUserIdTest() {
        List<PaymentDTO> list = paymentMapper.selectCompleteItemsByUserId(1L);
        log.info("유저 1번이 거래완료된 애들임 : {}", list);
    }
}
