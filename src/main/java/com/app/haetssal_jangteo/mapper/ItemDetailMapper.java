package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.dto.ItemDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ItemDetailMapper {

//    id로 상품 상세 정보 조회
    public Optional<ItemDetailDTO> selectById(Long id);

//    같은 가게 상품 개수 조회
    public int countSameStoreItem(Long id);
}
