package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.pagination.Criteria;
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

    // 상품 id로 해당 상품의 후기들 조회
    public List<ReviewDTO> selectByItemId(Long itemId, Criteria criteria);

    // 상품 id로 해당 상품의 후기 개수 조회
    public int selectCountByItemId(Long itemId);

    // 가게 id로 해당 가게의 상품 후기들 조회
    public List<ReviewDTO> selectByStoreId(Long storeId, Criteria criteria);

    // 가게 id로 해당 가게의 상품 후기 개수 조회
    public int selectCountByStoreId(Long storeId);
}
