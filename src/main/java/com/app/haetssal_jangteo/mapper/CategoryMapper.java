package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.CategoryVO;
import com.app.haetssal_jangteo.domain.SubCategoryVO;
import com.app.haetssal_jangteo.dto.CategoryDTO;
import com.app.haetssal_jangteo.dto.SubCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CategoryMapper {

//    카테고리 등록
    public void insert(CategoryDTO categoryDTO);

//    세부 카테고리 등록
    public void insertSub(SubCategoryDTO subCategoryDTO);

//    카테고리 삭제
    public void delete(Long id);

//    세부 카테고리 삭제
    public void deleteSub(Long id);

//    카테고리 전체 조회
    public List<CategoryVO> selectCateAll();

//    카테고리 하나 조회
    public Optional<CategoryVO> selectOneById(Long id);

//    상위 카테고리로 세부 카테고리 전체 조회
    public List<SubCategoryVO> selectByParentId(Long parentCategoryId);

//    세부 카테고리 하나 조회
    public Optional<SubCategoryVO> selectSubOneById(Long id);

}
