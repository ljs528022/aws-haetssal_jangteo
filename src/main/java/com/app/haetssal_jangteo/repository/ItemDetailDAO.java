package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.dto.ItemDetailDTO;
import com.app.haetssal_jangteo.mapper.ItemDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemDetailDAO {
    private final ItemDetailMapper itemDetailMapper;

    // 상품 상세 정보 조회
    public Optional<ItemDetailDTO> findItemDetailById(Long id) {
        return itemDetailMapper.selectById(id);
    }

}
