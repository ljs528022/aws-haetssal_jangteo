package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.FileItemVO;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileItemMapper {
    // 상품 이미지 등록
    public void insert(FileItemVO fileItemVO);
    // 상품 id로 이미지들 조회
    public List<FileItemDTO> selectImagesByItemId(Long itemId);

    // 상품 id와 타입으로 이미지들 조회
    public List<FileItemDTO> selectImagesByIdAndFileItemType(@Param("id") Long id,
                                                             @Param("type") String type);

    // 상품 이미지 삭제
    public void delete(Long id);
}
