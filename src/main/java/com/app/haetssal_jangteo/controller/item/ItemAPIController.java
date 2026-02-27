package com.app.haetssal_jangteo.controller.item;

import ch.qos.logback.core.model.Model;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import com.app.haetssal_jangteo.dto.ItemDescImageDTO;
import com.app.haetssal_jangteo.dto.ItemReviewDTO;
import com.app.haetssal_jangteo.mapper.ItemMapper;
import com.app.haetssal_jangteo.service.item.ItemService;
import com.app.haetssal_jangteo.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item/**")
@RequiredArgsConstructor
@Slf4j
public class ItemAPIController {
    private final ItemService itemService;
    private final ReviewService reviewService;

    @GetMapping("/images/{id}")
    public ItemDescImageDTO getDescImages(@PathVariable Long id) {
        log.info("이미지 받아올 상품 id >>>> {}", id);
        return itemService.getItemDescImages(id);
    }

    @GetMapping("/reviews/{id}")
    public ItemReviewDTO getItemReviews(@PathVariable Long id) {
        log.info("후기 받아올 상품 id >>>> {}", id);
        return reviewService.getReviewsByItemId(id);
    }
}
