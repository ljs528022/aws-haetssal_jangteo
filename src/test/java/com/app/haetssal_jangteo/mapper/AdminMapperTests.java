//package com.app.haetssal_jangteo.mapper;
//
//import com.app.haetssal_jangteo.common.enumeration.State;
//import com.app.haetssal_jangteo.dto.ItemDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//@Slf4j
//public class AdminMapperTest {
//
//    @Autowired
//    private AdminItemMapper adminMapper;
//
//    @Test
//    public void testUpdate() {
//        ItemDTO itemDTO = new ItemDTO();
//        itemDTO.setId(1L);
//        itemDTO.setItemName("배 박스");
//        itemDTO.setItemPrice("20000");
//        itemDTO.setItemType("normal");
//        itemDTO.setItemCategoryId(1L);
//        itemDTO.setItemState(State.ACTIVE);
//        itemDTO.setUpdatedDatetime("2016-01-24 00:00:00");
//
//        adminMapper.update(itemDTO.toVO());
//    }
//
////    @Test
////    public void testSelectAll() {
////        List<ItemDTO> itemList = adminMapper.selectAll();
////        log.info("{}.......", itemList);
////    }
//}
//
