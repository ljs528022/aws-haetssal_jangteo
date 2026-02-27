package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.FileReviewVO;
import com.app.haetssal_jangteo.dto.FileReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileReviewMapper {
    // 리뷰 이미지 등록
    public void insert(FileReviewVO fileReviewVO);

    // 리뷰 아이디로 이미지 조회
    public List<FileReviewDTO> selectImagesByReviewId(Long reviewId);
}
