package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.domain.TagVO;
import com.app.haetssal_jangteo.mapper.TagMapper;

import java.util.List;

public class TagDAO {
    private final TagMapper tagMapper;

    public TagDAO(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    //    추가
    public void save(TagVO tagVO) {
        tagMapper.insert(tagVO);
    }

    //    목록
    public List<TagVO> findAllByPostId(Long id){
        return tagMapper.selectAllByPostId(id);
    }

    //    전체 태그(중복 없이)
    public List<String> findAll(){
        return tagMapper.selectAll();
    }

    //    삭제
    public void delete(Long id){
        tagMapper.delete(id);
    }

    //    삭제(게시글 아이디)
    public void deleteByPostId(Long id){
        tagMapper.deleteByPostId(id);
    }
}
