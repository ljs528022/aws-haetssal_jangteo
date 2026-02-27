package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.Search;
import com.app.haetssal_jangteo.domain.StoreVO;
import com.app.haetssal_jangteo.dto.StoreDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminStoreMapper {

    //    전체 가게 조회 (파라미터 없음)
    List<StoreVO> selectAllStores();

    //    목록 (페이징 + 검색)
    List<StoreDTO> selectAll(@Param("criteria") Criteria criteria, @Param("search") Search search);

    //    전체 개수
    int selectTotal(@Param("search") Search search);

    //    지역 목록 조회 (distinct)
    List<String> selectDistinctRegions();

    //    수정
    void update(StoreDTO StoreDTO);
}

