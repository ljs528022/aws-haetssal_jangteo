package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.Search;
import com.app.haetssal_jangteo.domain.ItemVO;
import com.app.haetssal_jangteo.dto.ItemDTO;
import com.app.haetssal_jangteo.mapper.AdminItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminItemDAO {
    private final AdminItemMapper adminItemMapper;

    //  전체 개수
    public int findTotal(Search search) {
        return adminItemMapper.selectTotal(search);
    }

    //    상품 전체 조회
    public List<ItemDTO> findAll(Criteria criteria, Search search) {
        return adminItemMapper.selectAll(criteria, search);
    }

    //    카테고리 목록 조회
    public List<String> findCategories() {
        return adminItemMapper.selectDistinctCategories();
    }

    //    수정
    public void setItem(ItemVO itemVO){
        adminItemMapper.update(itemVO);

    }
}


