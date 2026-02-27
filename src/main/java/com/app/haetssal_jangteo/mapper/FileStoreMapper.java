package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.FileStoreVO;
import com.app.haetssal_jangteo.dto.FileStoreDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface FileStoreMapper {
    // 상점 프로필 이미지 등록
    public void insert(FileStoreVO fileStoreVO);
    // 상점 id로 프로필 이미지 조회
    public Optional<FileStoreDTO> selectById(Long storeId);
    // 상점 이미지 삭제
    public void delete(Long id);
}
