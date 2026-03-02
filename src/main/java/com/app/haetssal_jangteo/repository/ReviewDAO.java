package com.app.haetssal_jangteo.repository;

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

    //    판매자 상품 후기 목록
    public List<ReviewDTO> findReviewsBySellerId(Long userId) {
        return reviewMapper.selectAllReviewBySellerId(userId);
    }

//    상품 A의 리뷰목록
//    public List<ReviewDTO> findReviewsByItemId(Long itemId) {
//        return reviewMapper.selectAllByItemId(itemId);
//    }

    //    각 리뷰들에 이미지들 출력(리뷰 아이디로)
    public List<FileReviewDTO> findImagesInReview(Long reviewId) {
        return fileReviewMapper.selectImagesByReviewId(reviewId);
    }

    //    리뷰 수정
    public void updateReview(ReviewDTO reviewDTO) {
        reviewMapper.update(reviewDTO);
    }

    //    리뷰 상태 inactive로
    public void deleteReview(Long reviewId) {
        reviewMapper.deleteByReviewId(reviewId);
    }
}