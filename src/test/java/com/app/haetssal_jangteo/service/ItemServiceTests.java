package com.app.haetssal_jangteo.service;

import com.app.haetssal_jangteo.common.enumeration.FileItemType;
import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.common.exception.ItemNotFoundException;
import com.app.haetssal_jangteo.domain.ItemOptionVO;
import com.app.haetssal_jangteo.domain.ItemVO;
import com.app.haetssal_jangteo.dto.*;
import com.app.haetssal_jangteo.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
public class ItemServiceTests {
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private ItemDetailDAO itemDetailDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private FileDAO fileDAO;
    @Autowired
    private FileItemDAO fileItemDAO;

    @Test
    public void testSave() {
        // 상품 저장
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemStoreId(3L);
        itemDTO.setItemCategoryId(100L);
        itemDTO.setItemName("사과 박스");
        itemDTO.setItemPrice("15000");
        itemDTO.setItemStock("40");
        itemDTO.setItemContent("사과 1 BOX (10KG)");

        itemDAO.save(itemDTO);

        // 상품 옵션 저장
        ItemOptionDTO option = new ItemOptionDTO();
        option.setOptionItemId(itemDTO.getId());
        option.setOptionName("옵션2");
        option.setOptionDetail("option detail2");
        option.setOptionPrice("100000");
        option.setOptionStock("10");

        itemDAO.saveOption(option.toVO());

        // 파일 저장
        FileDTO fileDTO = new FileDTO();
        UUID uuid = UUID.randomUUID();
        fileDTO.setFileType(Filetype.IMAGE);
        fileDTO.setFileName(uuid.toString() + "_" + "file");
        fileDTO.setFileOriginName("itemImage");
        fileDTO.setFileSavedPath("../../path");
        fileDTO.setFileSize("100");

        fileDAO.save(fileDTO);

        // 상품 이미지 정보 저장
        FileItemDTO fileItemDTO = new FileItemDTO();
        fileItemDTO.setId(fileDTO.getId());
        fileItemDTO.setItemId(itemDTO.getId());
        fileItemDTO.setFileItemType(FileItemType.THUMBNAIL);

        fileItemDAO.save(fileItemDTO.toFileItemVO());

    }

    @Test
    public void testDetail() {
        Optional<ItemDetailDTO> itemDetailDTO = itemDetailDAO.findItemDetailById(7L);

        ItemDetailDTO dto = itemDetailDTO.get();

        List<ItemOptionDTO> options = itemDAO.findOptionsById(dto.getId())
                .stream().map((optionVO) -> toOptionDTO(optionVO)).collect(Collectors.toList());
        List<FileItemDTO> thumbnails = fileItemDAO.findImagesByIdAndFileItemType(dto.getId(), "thumbnail").stream().collect(Collectors.toList());

        dto.setItemOptions(options);
        dto.setItemThumbnails(thumbnails);

        log.info("{}.......", dto);
    }

    @Test
    public void testGetSameCategoryItems() {
        ItemVO currentItem = itemDAO.findById(7L).orElseThrow(ItemNotFoundException::new);

        List<ItemDTO> sameCategoryItems = itemDAO.findSameCategoryItems(
                currentItem.getItemCategoryId(),
                currentItem.getItemSubCategoryId(),
                currentItem.getId()
        );

        sameCategoryItems.stream().forEach((item) -> {
            item.setItemFiles(fileItemDAO.findImagesByIdAndFileItemType(item.getId(), "thumbnail"));
        });

        log.info("{}........", sameCategoryItems);
        log.info("{}........", sameCategoryItems.size());
    }

    @Test
    public void testFindTotal() {
        int count = itemDAO.findTotal(2L);
        log.info("가져온 상품 수 : {}", count);
    }

    public ItemOptionDTO toOptionDTO (ItemOptionVO option) {
        ItemOptionDTO itemOptionDTO = new ItemOptionDTO();
        itemOptionDTO.setId(option.getId());
        itemOptionDTO.setOptionItemId(option.getOptionItemId());
        itemOptionDTO.setOptionName(option.getOptionName());
        itemOptionDTO.setOptionDetail(option.getOptionDetail());
        itemOptionDTO.setOptionPrice(option.getOptionPrice());
        itemOptionDTO.setOptionStock(option.getOptionStock());

        return itemOptionDTO;
    }
}
