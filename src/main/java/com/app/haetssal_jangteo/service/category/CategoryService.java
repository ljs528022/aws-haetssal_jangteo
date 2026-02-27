package com.app.haetssal_jangteo.service.category;

import com.app.haetssal_jangteo.common.exception.CategoryNotFoundException;
import com.app.haetssal_jangteo.domain.CategoryVO;
import com.app.haetssal_jangteo.domain.SubCategoryVO;
import com.app.haetssal_jangteo.dto.CategoryDTO;
import com.app.haetssal_jangteo.dto.SubCategoryDTO;
import com.app.haetssal_jangteo.repository.CategoryDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CategoryService {
    private final CategoryDAO categoryDAO;

    // 상위 카테고리 조회
    public CategoryDTO findById(Long id) {
        CategoryDTO category = toDTO(categoryDAO.findById(id).orElseThrow(CategoryNotFoundException::new));
        return category;
    }

    // 상위 카테고리 전체 조회
    public List<CategoryDTO> findAll() {
        return categoryDAO.findAll().stream()
                .map(categoryVO -> toDTO(categoryVO)).collect(Collectors.toList());
    }

    // 하위 카테고리 조회
    public SubCategoryDTO findSubById(Long id) {
        SubCategoryDTO subCategory = toDTOSub(categoryDAO.findSubById(id).orElseThrow(CategoryNotFoundException::new));
        return subCategory;
    }

    // 상위 카테고리로 하위 전체 조회
    public List<SubCategoryDTO> findSubAllById(Long id) {
        return categoryDAO.findSubAllById(id).stream()
                .map(subCategoryVO -> toDTOSub(subCategoryVO)).collect(Collectors.toList());
    }

    public CategoryDTO toDTO (CategoryVO categoryVO) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryVO.getId());
        categoryDTO.setCategoryName(categoryVO.getCategoryName());

        return categoryDTO;
    }

    public SubCategoryDTO toDTOSub (SubCategoryVO subCategoryVO) {
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.setId(subCategoryVO.getId());
        subCategoryDTO.setCategoryName(subCategoryVO.getCategoryName());
        subCategoryDTO.setParentCategoryId(subCategoryVO.getParentCategoryId());

        return subCategoryDTO;
    }

}
