package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.domain.CategoryVO;
import com.app.haetssal_jangteo.domain.SubCategoryVO;
import com.app.haetssal_jangteo.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryDAO {
    private final CategoryMapper categoryMapper;

//    특정 상위 카테고리 조회
    public Optional<CategoryVO> findById(Long id) {
        return categoryMapper.selectOneById(id);
    }

//    상위 카테고리 전체 조회
    public List<CategoryVO> findAll() {
        return categoryMapper.selectCateAll();
    }

//    특정 하위 카테고리 조회
    public Optional<SubCategoryVO> findSubById(Long id) {
        return categoryMapper.selectSubOneById(id);
    }

//    상위 카테고리로 하위 카테고리 전체 조회
    public List<SubCategoryVO> findSubAllById(Long id) {
        return categoryMapper.selectByParentId(id);
    }
}
