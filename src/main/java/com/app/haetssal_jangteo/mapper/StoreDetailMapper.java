package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.dto.StoreDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface StoreDetailMapper {
//    가게 상세 조회
    public Optional<StoreDetailDTO> selectById(Long id);

}
