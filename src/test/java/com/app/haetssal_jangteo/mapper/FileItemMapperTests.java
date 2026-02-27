package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.enumeration.FileItemType;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class FileItemMapperTests {

    @Autowired
    private FileItemMapper fileItemMapper;

    @Test
    public void testInsert() {
        FileItemDTO fileItemDTO = new FileItemDTO();
        fileItemDTO.setId(17L);
        fileItemDTO.setItemId(7L);
        fileItemDTO.setFileItemType(FileItemType.THUMBNAIL);

        fileItemMapper.insert(fileItemDTO.toFileItemVO());
    }

    @Test
    public void testSelectImagesByItemId() {
        List<FileItemDTO> foundImages = fileItemMapper.selectImagesByItemId(3L);
        log.info("{}.........", foundImages);
    }

    @Test
    public void testSelectImagesByIdAndFIleItemType() {
        List<FileItemDTO> foundImages = fileItemMapper.selectImagesByIdAndFileItemType(3L, "seller-info");
        log.info("{}.....", foundImages);
    }
}
