package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.dto.FileReviewDTO;
import com.app.haetssal_jangteo.dto.ReviewDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ReviewMapperTests {

    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private FileReviewMapper fileReviewMapper;

    @Test
    public void insertTest() {
        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setReviewItemId(2L);
        reviewDTO.setReviewUserId(2L);
        reviewDTO.setReviewScoreQuality(3);
        reviewDTO.setReviewScoreDelivery(2);
        reviewDTO.setReviewScoreKind(2);
        reviewDTO.setReviewContent("테스트임");

        reviewMapper.insert(reviewDTO);
    }

    @Test
    public void selectAllReviewByUserIdTest() {
        List<ReviewDTO> list = reviewMapper.selectAllReviewByUserId(2L);
        log.info("id2가 써버린 리뷰들이요: {}", list);
    }

    @Test
    public void selectReviewCountByUserIdTest() {
        int count = reviewMapper.selectReviewCountByUserId(2L);
        log.info("{}개 씀", count);
    }

    @Test
    public void insertFileReviewTest() {
        FileReviewDTO fileReviewDTO = new FileReviewDTO();
        fileReviewDTO.setFileId(4L);
        fileReviewDTO.setReviewId(2L);
        fileReviewMapper.insert(fileReviewDTO.toFileReviewVO());
    }

    @Test
    public void selectImagesByReviewIdTest() {
        List<FileReviewDTO> list = fileReviewMapper.selectImagesByReviewId(2L);
        log.info("2번리뷰의 사진이에요 {}", list);
    }

    @Test
    public void testSelectByItemId() {
        List<ReviewDTO> foundReviews = reviewMapper.selectByItemId(5L, null);
        log.info("{}...", foundReviews);
    }

    @Test
    public void testSelectCountByItemId() {
        int count = reviewMapper.selectCountByItemId(5L);
        log.info("찾은 후기 개수 : {}", count);
    }

    @Test
    public void testSelectByStoreId() {
        List<ReviewDTO> foundReviews = reviewMapper.selectByStoreId(129L, null);
        log.info("{}...", foundReviews);
    }

    @Test
    public void testSelectCountByStoreId() {
        int count = reviewMapper.selectCountByStoreId(129L);
        log.info("찾은 후기 개수 : {}", count);
    }
}
