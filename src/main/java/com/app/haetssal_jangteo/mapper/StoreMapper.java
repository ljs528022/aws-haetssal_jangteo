package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.StoreSearch;
import com.app.haetssal_jangteo.domain.StoreVO;
import com.app.haetssal_jangteo.dto.StoreDTO;
import com.app.haetssal_jangteo.dto.StoreDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface StoreMapper {
//    가게 등록
    public void insert(StoreDTO storeDTO);

//    가게 정보 수정
    public void update(StoreVO storeVO);

//    가게 상태 변경 (CLOSE, DENIED)
    public void updateState(@Param("id") Long id, @Param("state") String state);

//    가게 등록 승인
    public void updateIsConfirmed(Long id);

//    전체 가게 조회
    public List<StoreDTO> selectAll();

//    장터 id로 소속 가게들 조회
    public List<StoreDTO> selectByMarketId(Long marketId);

//    id로 가게 조회
    public Optional<StoreVO> selectById(Long id);

//    가게 이름으로 가게 조회
    public Optional<StoreVO> selectByStoreName(String storeName);

//    가게 소유주 id로 가게 조회
    public Optional<StoreVO> selectByStoreOwnerId(Long storeOwnerId);

//    검색 값에 따라 가게 조회
    public List<StoreDTO> selectBySearch(@Param("criteria")Criteria criteria, @Param("search")StoreSearch storeSearch);

//    검색 값으로 조회된 가게 수 조회
    public int selectTotal(@Param("search") StoreSearch storeSearch);

//    가게 비활성화
    public void delete(Long id);

}
