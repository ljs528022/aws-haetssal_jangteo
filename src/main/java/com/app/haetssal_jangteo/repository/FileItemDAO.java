package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.domain.FileItemVO;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import com.app.haetssal_jangteo.mapper.FileItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileItemDAO {
    private final FileItemMapper fileItemMapper;

    // 아이템 이미지 등록
    public void save(FileItemVO fileItemVO) {
        fileItemMapper.insert(fileItemVO);
    }

    // 상품 id로 상품 이미지 조회
    public List<FileItemDTO> findImagesById(Long itemId) {
        return fileItemMapper.selectImagesByItemId(itemId);
    }

    // 상품 id와 타입으로 이미지 조회
    public List<FileItemDTO> findImagesByIdAndFileItemType(Long itemId, String type) {
        return fileItemMapper.selectImagesByIdAndFileItemType(itemId, type);
    }

    // 상품 이미지 삭제
    public void delete(Long id) {
        fileItemMapper.delete(id);
    }
}