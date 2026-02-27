package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.StoreSearch;
import com.app.haetssal_jangteo.domain.StoreVO;
import com.app.haetssal_jangteo.dto.StoreDTO;
import com.app.haetssal_jangteo.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StoreDAO {
    private final StoreMapper storeMapper;

    // 가게 등록
    public void save(StoreDTO storeDTO) {
        storeMapper.insert(storeDTO);
    }

    // 가게 정보 수정
    public void update(StoreVO storeVO) {
        storeMapper.update(storeVO);
    }

    // 가게 상태 변경
    public void setState(Long id, String state) {
        storeMapper.updateState(id, state);
    };

    // 가게 등록 승인
    public void changeIsConfirmed(Long id) {
        storeMapper.updateIsConfirmed(id);
    }

    // 전체 가게 조회
    public List<StoreDTO> findAll() {
        return storeMapper.selectAll();
    }

    // 장터 id로 소속 가게들 조회
    public List<StoreDTO> findStoresByMarketId(Long marketId) {
        return storeMapper.selectByMarketId(marketId);
    }

    // id로 가게 조회
    public Optional<StoreVO> findById(Long id) {
        return storeMapper.selectById(id);
    }

    // 가게 이름으로 조회
    public Optional<StoreVO> findByStoreName(String storeName) {
        return storeMapper.selectByStoreName(storeName);
    }

    // 가게 소유주 id로 조회
    public Optional<StoreVO> findByStoreOwnerId(Long ownerId) {
        return storeMapper.selectByStoreOwnerId(ownerId);
    }

    // 검색 값에 따라 가게 조회
    public List<StoreDTO> findBySearch(Criteria criteria, StoreSearch storeSearch) {
        return storeMapper.selectBySearch(criteria, storeSearch);
    }

    // 검색 값에 따른 가게 수 조회
    public int findTotal(StoreSearch storeSearch) {
        return storeMapper.selectTotal(storeSearch);
    }

    // 가게 비활성화
    public void delete(Long id) {
        storeMapper.delete(id);
    };

}
