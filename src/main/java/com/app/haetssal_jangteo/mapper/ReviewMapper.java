package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    // 리뷰 쓰기
    public void insert(ReviewDTO reviewDTO);

    // 유저가 작성한 리뷰 목록
    public List<ReviewDTO> selectAllReviewByUserId(Long userId);

    // 유저가 작성한 리뷰 개수
    public int selectReviewCountByUserId(Long userId);

    // 특정 상품의 리뷰 목록
//    public List<ReviewDTO> selectAllByItemId(Long itemId);

    // 가게 id로 해당 가게의 상품 후기들 조회
    public List<ReviewDTO> selectByStoreId(Long storeId);

    // 셀러페이지에 후기목록
    public List<ReviewDTO> selectAllReviewBySellerId(Long userId);

    // 리뷰 수정
    public void update(ReviewDTO reviewDTO);

    // 리뷰 삭제
    public void deleteByReviewId(Long id);
}