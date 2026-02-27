package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.CategoryVO;
import com.app.haetssal_jangteo.domain.SubCategoryVO;
import com.app.haetssal_jangteo.dto.CategoryDTO;
import com.app.haetssal_jangteo.dto.SubCategoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class CategoryMapperTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void testInsert() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(100L);
        categoryDTO.setCategoryName("축산물");

        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setId(200L);
        categoryDTO2.setCategoryName("농산물");

        CategoryDTO categoryDTO3 = new CategoryDTO();
        categoryDTO3.setId(300L);
        categoryDTO3.setCategoryName("수산물");

        categoryMapper.insert(categoryDTO);
        categoryMapper.insert(categoryDTO2);
        categoryMapper.insert(categoryDTO3);
    }

    @Test
    public void testInsertSub() {
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.setId(101L);
        subCategoryDTO.setCategoryName("돼지고기");
        subCategoryDTO.setParentCategoryId(100L);

        SubCategoryDTO subCategoryDTO2 = new SubCategoryDTO();
        subCategoryDTO2.setId(102L);
        subCategoryDTO2.setCategoryName("소고기");
        subCategoryDTO2.setParentCategoryId(100L);

        SubCategoryDTO subCategoryDTO3 = new SubCategoryDTO();
        subCategoryDTO3.setId(103L);
        subCategoryDTO3.setCategoryName("오리고기");
        subCategoryDTO3.setParentCategoryId(100L);

        categoryMapper.insertSub(subCategoryDTO);
        categoryMapper.insertSub(subCategoryDTO2);
        categoryMapper.insertSub(subCategoryDTO3);
    }

    @Test
    public void testDelete() {
        categoryMapper.delete(300L);
    }

    @Test
    public void testDeleteSub() {
        categoryMapper.deleteSub(103L);
    }

    @Test
    public void testSelectCateAll() {
        List<CategoryVO> categoryVOList = categoryMapper.selectCateAll();
        log.info("{}...........", categoryVOList);
    }

    @Test
    public void testSelectOneById() {
        Optional<CategoryVO> foundCategory = categoryMapper.selectOneById(100L);
        log.info("{}..........", foundCategory);
    }

    @Test
    public void testSelectByParentId() {
        List<SubCategoryVO> subCategoryList = categoryMapper.selectByParentId(100L);
        log.info("{}...........", subCategoryList);
    }

    @Test
    public void testSelectSubOneById() {
        Optional<SubCategoryVO> subCategory = categoryMapper.selectSubOneById(101L);
        log.info("{}...........", subCategory);
    }

}
