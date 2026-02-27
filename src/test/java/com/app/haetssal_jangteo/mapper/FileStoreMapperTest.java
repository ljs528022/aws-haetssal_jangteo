package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.dto.FileStoreDTO;
import com.app.haetssal_jangteo.dto.FileUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FileStoreMapperTest {

    @Autowired
    private FileStoreMapper fileStoreMapper;

    @Test
    public void testInsert() {
        FileStoreDTO fileStoreDTO = new FileStoreDTO();
        fileStoreDTO.setId(16L);
        fileStoreDTO.setStoreId(4L);

        fileStoreMapper.insert(fileStoreDTO.toFileStoreVO());
    }

    @Test
    public void testDelete() {
        fileStoreMapper.delete(16L);
    }
}
