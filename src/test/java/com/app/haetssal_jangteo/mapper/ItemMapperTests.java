package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.enumeration.State;
import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.domain.ItemOptionVO;
import com.app.haetssal_jangteo.domain.ItemVO;
import com.app.haetssal_jangteo.dto.ItemDTO;
import com.app.haetssal_jangteo.dto.ItemOptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class ItemMapperTests {

    @Autowired
    private ItemMapper itemMapper;

    @Test
    public void testInsert() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemStoreId(2L);
        itemDTO.setItemCategoryId(100L);
        itemDTO.setItemName("사과 박스");
        itemDTO.setItemPrice("15000");
        itemDTO.setItemStock("40");
        itemDTO.setItemContent("사과 1 BOX (10KG)");

        itemMapper.insert(itemDTO);
    }

    @Test
    public void testInsertOption() {
        ItemOptionDTO itemOptionDTO = new ItemOptionDTO();
        itemOptionDTO.setOptionItemId(7L);
        itemOptionDTO.setOptionName("옵션1");
        itemOptionDTO.setOptionDetail("사과 박스 1 BOX (16KG)");
        itemOptionDTO.setOptionPrice("20000");
        itemOptionDTO.setOptionStock("15");

        ItemOptionDTO itemOptionDTO2 = new ItemOptionDTO();
        itemOptionDTO2.setOptionItemId(7L);
        itemOptionDTO2.setOptionName("옵션2");
        itemOptionDTO2.setOptionDetail("사과 박스 1 BOX (16KG)");
        itemOptionDTO2.setOptionPrice("25000");
        itemOptionDTO2.setOptionStock("20");

        ItemOptionDTO itemOptionDTO3 = new ItemOptionDTO();
        itemOptionDTO3.setOptionItemId(7L);
        itemOptionDTO3.setOptionName("옵션3");
        itemOptionDTO3.setOptionDetail("사과 박스 1 BOX (16KG)");
        itemOptionDTO3.setOptionPrice("30000");
        itemOptionDTO3.setOptionStock("35");

        ItemOptionDTO itemOptionDTO4 = new ItemOptionDTO();
        itemOptionDTO4.setOptionItemId(7L);
        itemOptionDTO4.setOptionName("옵션4");
        itemOptionDTO4.setOptionDetail("사과 박스 1 BOX (16KG)");
        itemOptionDTO4.setOptionPrice("35000");
        itemOptionDTO4.setOptionStock("30");

        itemMapper.insertOption(itemOptionDTO.toVO());
        itemMapper.insertOption(itemOptionDTO2.toVO());
        itemMapper.insertOption(itemOptionDTO3.toVO());
        itemMapper.insertOption(itemOptionDTO4.toVO());
    }

    @Test
    public void testUpdate() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(1L);
        itemDTO.setItemName("배 박스");
        itemDTO.setItemPrice("20000");
        itemDTO.setItemStock("20");
        itemDTO.setItemContent("배 1 BOX (10KG)");

        itemMapper.update(itemDTO.toVO());
    }

    @Test
    public void testUpdateState() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(1L);
        itemDTO.setItemState(State.INACTIVE);

        itemMapper.updateState(itemDTO);
    }

    @Test
    public void testUpdateViewCount() {
        itemMapper.updateViewCount(1L);
    }

    @Test
    public void testSelectAll() {
        List<ItemDTO> itemList = itemMapper.selectAll();
        log.info("{}.......", itemList);
    }

    @Test
    public void testSelectById() {
        Optional<ItemVO> foundItem = itemMapper.selectById(3L);
        log.info("{}...........", foundItem);
    }

    @Test
    public void testSelectOptionById() {
        Optional<ItemOptionVO> foundOption = itemMapper.selectOptionById(7L);
        log.info("받아온 옵션 정보: {}", foundOption);
    }

    @Test
    public void testSelectAllOptions() {
        List<ItemOptionVO> options = itemMapper.selectAllOptions(1L);
        log.info("{}..........", options);
    }

    @Test
    public void testSelectSameCategoryItems() {
        List<ItemDTO> itemList = itemMapper.selectSameCategoryItems(100L, 101L, 7L);
        log.info("{}.......", itemList);
    }

    @Test
    public void testSelectByStoreId() {
        Criteria criteria = new Criteria(1, 10);
        List<ItemDTO> itemList = itemMapper.selectByStoreId(2L, criteria);

        log.info("{}........", itemList);
    }

    @Test
    public void testSelectTotal() {
        int count = itemMapper.selectTotalByStoreId(2L);
        log.info("가져온 상품 수: {}", count);
    }

}
