package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.domain.ItemOptionVO;
import com.app.haetssal_jangteo.domain.ItemVO;
import com.app.haetssal_jangteo.dto.ItemDTO;
import com.app.haetssal_jangteo.dto.ItemOptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {
//    상품 등록
    public void insert(ItemDTO itemDTO);

//    상품 옵션 등록
    public void insertOption(ItemOptionVO itemOptionVO);

//    상품 옵션 삭제
    public void deleteOption(Long id);

//    상품 옵션 삭제(상품 id)
    public void deleteOptionByItemId(Long itemId);

//    상품 수정
    public void update(ItemVO itemVO);

//    상품 상태 변경
    public void updateState(ItemDTO itemDTO);

//    상품 조회수 증가
    public void updateViewCount(Long id);

//    전체 상품 조회
    public List<ItemDTO> selectAll();

//    상품 id로 상품 하나 조회
    public Optional<ItemVO> selectById(Long id);

//    상품 id로 해당 상품 옵션 전체 조회
    public List<ItemOptionVO> selectAllOptions(Long optionItemId);

//    옵션 id로 옵션 조회
    public Optional<ItemOptionVO> selectOptionById(Long id);

//    가게 id로 상품들 조회
    public List<ItemDTO> selectByStoreId(@Param("storeId") Long storeId, @Param("criteria")Criteria criteria);

//    카테고리 id로 상품들 조회

//    특정 상품과 같은 카테고리의 상품 조회
    public List<ItemDTO> selectSameCategoryItems(
            @Param("categoryId") Long categoryId,
            @Param("subCategoryId") Long subCategoryId,
            @Param("thisItemId") Long thisItemId
    );

//    상품 id로 상품 개수 조회
    public int selectTotalByStoreId(Long storeId);

//    상품 삭제(soft delete)
    public void delete(Long id);
}
