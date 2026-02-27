package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.dto.FileUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class FileUserMapperTests {

    @Autowired
    private FileUserMapper fileUserMapper;

    @Test
    public void testInsert() {
        FileUserDTO fileUserDTO = new FileUserDTO();
        fileUserDTO.setId(15L);
        fileUserDTO.setUserId(2L);

        fileUserMapper.insert(fileUserDTO.toFileUserVO());
    }

    @Test
    public void testSelectById() {
         Optional<FileUserDTO> foundUserImage = fileUserMapper.selectById(2L);
         log.info("{}......", foundUserImage);
    }
}
