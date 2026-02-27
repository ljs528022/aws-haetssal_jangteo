package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.dto.StoreDetailDTO;
import com.app.haetssal_jangteo.mapper.StoreDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StoreDetailDAO {
    private final StoreDetailMapper storeDetailMapper;

    // 가게 상세 정보 조회
    public Optional<StoreDetailDTO> findById(Long id) {
        return storeDetailMapper.selectById(id);
    }
}
