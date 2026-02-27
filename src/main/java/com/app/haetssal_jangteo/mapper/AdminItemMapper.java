package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.Search;
import com.app.haetssal_jangteo.domain.ItemVO;
import com.app.haetssal_jangteo.domain.MarketVO;
import com.app.haetssal_jangteo.dto.ItemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminItemMapper {
    //    전체 상품 조회
    public List<ItemDTO> selectAllItems();

    //    목록
    public List<ItemDTO> selectAll(@Param("criteria") Criteria criteria, @Param("search") Search search);

    //    전체 개수
    public int selectTotal(@Param("search") Search search);

    //    카테고리 목록 조회 (distinct)
    List<String> selectDistinctCategories();

    //     수정
    public void update(ItemVO itemVO);
}