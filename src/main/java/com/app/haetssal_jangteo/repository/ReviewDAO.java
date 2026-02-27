package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.domain.FileReviewVO;
import com.app.haetssal_jangteo.dto.FileReviewDTO;
import com.app.haetssal_jangteo.dto.ReviewDTO;
import com.app.haetssal_jangteo.mapper.FileReviewMapper;
import com.app.haetssal_jangteo.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewDAO {
    private final ReviewMapper reviewMapper;
    private final FileReviewMapper fileReviewMapper;

//    리뷰쓰기
    public void saveReview(ReviewDTO reviewDTO) {
        reviewMapper.insert(reviewDTO);
    }

//    리뷰쓸때 사진도 달기
    public void saveImagesInReview(FileReviewVO fileReviewVO) {
        fileReviewMapper.insert(fileReviewVO);
    }

//    리뷰개수
    public int findReviewCount(Long userId){
        return reviewMapper.selectReviewCountByUserId(userId);
    }

//    유저가쓴 리뷰목록
    public List<ReviewDTO> findReviewsByUserId(Long userId) {
        return reviewMapper.selectAllReviewByUserId(userId);
    }

    // 상품 id로 관련 후기들 조회
    public List<ReviewDTO> findReviewsByItemId(Long itemId, Criteria criteria) {
        return reviewMapper.selectByItemId(itemId, criteria);
    }

    // 상품 id로 해당 상품 후기 개수 조회
    public int getReviewCountByItemId(Long itemId) {
        return reviewMapper.selectCountByItemId(itemId);
    }

    // 가게 id로 해당 가게의 상품 후기 조회
    public List<ReviewDTO> findReviewsByStoreId(Long storeId, Criteria criteria) {
        return reviewMapper.selectByStoreId(storeId, criteria);
    }

    // 가게 id로 해당 가게의 상품 후기 개수 조회
    public int getReviewCountByStoreId(Long storeId) {
        return reviewMapper.selectCountByStoreId(storeId);
    }

//    각 리뷰들에 이미지들 출력(리뷰 아이디로)
    public List<FileReviewDTO> findImagesInReview(Long reviewId) {
        return fileReviewMapper.selectImagesByReviewId(reviewId);
    }
}
