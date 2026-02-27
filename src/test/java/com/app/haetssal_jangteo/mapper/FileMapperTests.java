package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.domain.FileVO;
import com.app.haetssal_jangteo.dto.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class FileMapperTests {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Test
    public void testInsert() {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileType(Filetype.IMAGE);
        fileDTO.setFileName("itemImages");
        fileDTO.setFileOriginName("originProfile3");
        fileDTO.setFileSavedPath("../../path3");
        fileDTO.setFileSize("10000");

        fileMapper.insert(fileDTO);
    }

    @Test
    public void testSelectById() {
        Optional<FileVO> foundFile = fileMapper.selectById(2L);
        log.info("{}....", foundFile);
    }

    @Test
    public void testDelete() {
        fileMapper.delete(16L);
    }

}
