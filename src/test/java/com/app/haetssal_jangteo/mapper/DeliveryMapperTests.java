package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.dto.DeliveryDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class DeliveryMapperTests {

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Test
    public void inserTest() {
        DeliveryDTO deliveryDTO = new DeliveryDTO();

        deliveryDTO.setUserId(2L);
        deliveryDTO.setDeliveryAddress("문정로 1-1");
        deliveryDTO.setDeliveryDetailAddress("101동1 01호");
        deliveryDTO.setDeliveryReceiver("수령인이름");
        deliveryDTO.setReceiverPhone("01012341234");

        deliveryMapper.insert(deliveryDTO);
    }

    @Test
    public void deleteTest() {
        deliveryMapper.delete(7L);
    }

    @Test
    public void selectAllByUserIdTest() {
        List<DeliveryDTO> list = deliveryMapper.selectAllByUserId(2L);
        log.info("배송지 목록이요: {}", list);
    }
}
