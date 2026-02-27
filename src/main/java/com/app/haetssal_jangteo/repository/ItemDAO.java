package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.domain.ItemOptionVO;
import com.app.haetssal_jangteo.domain.ItemVO;
import com.app.haetssal_jangteo.dto.ItemDTO;
import com.app.haetssal_jangteo.dto.ItemOptionDTO;
import com.app.haetssal_jangteo.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemDAO {
    private final ItemMapper itemMapper;

//    상품 등록
    public void save(ItemDTO itemDTO) {
        itemMapper.insert(itemDTO);
    }

//    상품 옵션 등록
    public void saveOption(ItemOptionVO itemOptionVO) {
        itemMapper.insertOption(itemOptionVO);
    }

//    상품 옵션 삭제
    public void deleteOption(Long id) {
        itemMapper.deleteOption(id);
    }

//    상품 옵션 삭제(상품 id)
    public void deleteOptionByItemId(Long itemId) {
        itemMapper.deleteOptionByItemId(itemId);
    }

//    상품 id로 상품 하나 조회
    public Optional<ItemVO> findById(Long id) {
        return itemMapper.selectById(id);
    }

//    옵션 id로 옵션 하나 조회
    public Optional<ItemOptionVO> findOptionById(Long id) {
        return itemMapper.selectOptionById(id);
    };

//    상품 id로 해당 상품 옵션들 조회
    public List<ItemOptionVO> findOptionsById(Long id) {
        return itemMapper.selectAllOptions(id);
    }

//    가게 id로 상품들 조회
    public List<ItemDTO> findByStoreId(Long storeId, Criteria criteria) {
        return itemMapper.selectByStoreId(storeId, criteria);
    }

//    상품 전체 조회
    public List<ItemDTO> findAll() {
        return itemMapper.selectAll();
    }

//    특정 상품과 같은 카테고리의 상품 조회
    public List<ItemDTO> findSameCategoryItems(Long categoryId, Long subCategoryId, Long thisItemId) {
        return itemMapper.selectSameCategoryItems(categoryId, subCategoryId, thisItemId);
    }

//    상품 조회 수 증가
    public void increaseViewCount(Long id) {
        itemMapper.updateViewCount(id);
    }

//    상품 수정
    public void update(ItemVO itemVO) {
        itemMapper.update(itemVO);
    }

//    상품 개수 조회
    public int findTotal(Long id) {
        return itemMapper.selectTotalByStoreId(id);
    }

//    상품 삭제
    public void delete(Long id) {
        itemMapper.delete(id);
    }
}
