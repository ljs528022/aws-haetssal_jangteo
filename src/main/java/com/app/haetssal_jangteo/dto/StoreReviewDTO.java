package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString
@NoArgsConstructor
public class StoreReviewDTO {
    private List<ReviewDTO> storeReviews;
    private Criteria criteria;
    private int total;
}
