package com.app.haetssal_jangteo.service;

import com.app.haetssal_jangteo.dto.PaymentDTO;
import com.app.haetssal_jangteo.dto.ReviewDTO;
import com.app.haetssal_jangteo.service.review.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class MypageReviewServiceTests {
    @Autowired
    private ReviewService reviewService;

    @Test
    public void getCompletesTest() {
        List<PaymentDTO> completes = reviewService.getCompletesListByUserId(2L);
        log.info("어떤유저의 거래완료된 놈들: {}", completes);
    }

    @Test
    public void writeReviewTest() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewItemId(1L);
        reviewDTO.setReviewUserId(5L);
        reviewDTO.setReviewScoreQuality(5);
        reviewDTO.setReviewScoreDelivery(4);
        reviewDTO.setReviewScoreKind(5);
        reviewDTO.setReviewContent("리뷰써지나 테스트 제발");

        reviewService.writeReview(reviewDTO, new ArrayList<>());
        log.info("리뷰의 id는 {}", reviewDTO.getId());
    }

    @Test
    public void getReviewListTest() {
        List<ReviewDTO> list = reviewService.getReviewListByUserId(5L);
        log.info("리뷰 목록 => {}", list);
    }

    @Test
    public void getReviewCountTest() {
        int count = reviewService.getReivewCountByUserId(5L);
        log.info("이사람의 리뷰 개수는? {} 개", count);
    }
}
