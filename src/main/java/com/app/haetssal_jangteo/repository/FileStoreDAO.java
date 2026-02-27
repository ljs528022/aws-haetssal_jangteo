package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.domain.FileStoreVO;
import com.app.haetssal_jangteo.dto.FileStoreDTO;
import com.app.haetssal_jangteo.mapper.FileStoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FileStoreDAO {
    private final FileStoreMapper fileStoreMapper;

    // 상점 이미지 등록
    public void save(FileStoreVO fileStoreVO) {
        fileStoreMapper.insert(fileStoreVO);
    }

    // 상점 id로 상점 이미지 조회
    public Optional<FileStoreDTO> findByStoreId(Long storeId) {
       return fileStoreMapper.selectById(storeId);
    }

    // 상점 이미지 삭제
    public void delete(Long id) {
        fileStoreMapper.delete(id);
    };

}
