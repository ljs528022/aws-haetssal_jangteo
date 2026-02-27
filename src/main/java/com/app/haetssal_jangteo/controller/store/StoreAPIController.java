package com.app.haetssal_jangteo.controller.store;

import com.app.haetssal_jangteo.common.search.StoreSearch;
import com.app.haetssal_jangteo.dto.*;
import com.app.haetssal_jangteo.service.category.CategoryService;
import com.app.haetssal_jangteo.service.item.ItemService;
import com.app.haetssal_jangteo.service.market.MarketService;
import com.app.haetssal_jangteo.service.review.ReviewService;
import com.app.haetssal_jangteo.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/store/**")
@RequiredArgsConstructor
@Slf4j
public class StoreAPIController {
    private final StoreService storeService;
    private final MarketService marketService;
    private final CategoryService categoryService;
    private final ItemService itemService;
    private final ReviewService reviewService;

    @GetMapping("region/{region}")
    public List<MarketDTO> getMarkets(@PathVariable String region) {
        log.info("받아온 지역 정보: {}", region);
        return marketService.findByRegion(region);
    }

    @GetMapping("category")
    public List<CategoryDTO> getCategories() {
        return categoryService.findAll();
    }

    @GetMapping("list/{page}")
    public StoreWithPagingDTO list(@PathVariable int page, StoreSearch storeSearch) {
        log.info(storeSearch.toString());
        return storeService.findBySearch(page, storeSearch);
    }

    @GetMapping("detail/items/{page}")
    public ItemWithPagingDTO getItemsForDetail(@PathVariable int page, Long id) {
        log.info("상품 받아올 가게 id: {}", id);
        return itemService.getItemsForDetail(id, page);
    }

    @GetMapping("reviews/{page}")
    public StoreReviewDTO getReviewsForDetail(@PathVariable int page, Long id) {
        log.info("후기 받아올 가게 id: {}", id);
        return reviewService.getReviewsByStoreId(id, page);
    }

}
