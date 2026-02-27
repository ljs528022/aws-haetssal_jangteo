package com.app.haetssal_jangteo.service;

import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.StoreSearch;
import com.app.haetssal_jangteo.dto.*;
import com.app.haetssal_jangteo.repository.*;
import com.app.haetssal_jangteo.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
public class StoreServiceTests {

    @Autowired
    private StoreDAO storeDAO;
    @Autowired
    private StoreDetailDAO storeDetailDAO;
    @Autowired
    private FileDAO fileDAO;
    @Autowired
    private FileItemDAO fileItemDAO;
    @Autowired
    private FileStoreDAO fileStoreDAO;
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private ReviewDAO reviewDAO;

    @Test
    public void testSave() {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreMarketId(4L);
        storeDTO.setStoreOwnerId(2L);
        storeDTO.setStoreCategoryId(200L);
        storeDTO.setStoreName("테스트 장터2");
        storeDTO.setStoreIntro("테스트 장터 설명2");
        storeDTO.setStoreAddress("서울시 중구...");

        storeDAO.save(storeDTO);

        // 파일 저장
        FileDTO fileDTO = new FileDTO();
        UUID uuid = UUID.randomUUID();
        fileDTO.setFileType(Filetype.IMAGE);
        fileDTO.setFileName(uuid.toString() + "_" + "storeProfile");
        fileDTO.setFileOriginName("image02");
        fileDTO.setFileSavedPath("../../path");
        fileDTO.setFileSize("100");

        fileDAO.save(fileDTO);

        // 상품 이미지 정보 저장
        FileStoreDTO fileStoreDTO = new FileStoreDTO();
        fileStoreDTO.setId(fileDTO.getId());
        fileStoreDTO.setStoreId(storeDTO.getId());

        fileStoreDAO.save(fileStoreDTO.toFileStoreVO());
    }

    @Test
    public void testUpdate() {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(5L);
        storeDTO.setStoreCategoryId(500L);
        storeDTO.setStoreName("테스트 수정 장터3");
        storeDTO.setStoreIntro("테스트 수정 장터 설명3");
        storeDTO.setStoreAddress("서울 성동구3");
    }

    @Test
    public void testFindBySearch() {
        StoreSearch storeSearch = new StoreSearch();
        storeSearch.setRegion("서울");
        storeSearch.setMarketId(4L);
        storeSearch.setCategoryId(200L);
        storeSearch.setOrderValue("desc");

        StoreWithPagingDTO storeWithPagingDTO = new StoreWithPagingDTO();
        Criteria criteria = new Criteria(1, storeDAO.findTotal(storeSearch));

        List<StoreDTO> stores = storeDAO.findBySearch(criteria, storeSearch);

        criteria.setHasMore(stores.size() > criteria.getRowCount());
        storeWithPagingDTO.setCriteria(criteria);

        if(criteria.isHasMore()) {
            stores.remove(stores.size() - 1);
        }

        stores.forEach(storeDTO -> {
            storeDTO.setCreatedDatetime(DateUtils.toRelativeTime(storeDTO.getCreatedDatetime()));
        });
        storeWithPagingDTO.setStores(stores);

        log.info("{}....", storeWithPagingDTO);
    }

    @Test
    public void detail() {
        Optional<StoreDetailDTO> storeDetailDTO = storeDetailDAO.findById(129L);

        if(storeDetailDTO.isPresent()) {
            StoreDetailDTO dto = storeDetailDTO.get();

            // item 가져오기 + 썸내일 같이 가져오기
            List<ItemDTO> items = itemDAO.findByStoreId(dto.getId(), null).stream()
                    .map(itemDTO -> {
                        List<FileItemDTO> thumbnails = fileItemDAO.findImagesByIdAndFileItemType(itemDTO.getId(), "thumbnail").stream().collect(Collectors.toList());
                        if(!thumbnails.isEmpty()) {
                            itemDTO.setItemFiles(thumbnails);
                        }
                        return itemDTO;
                    }).collect(Collectors.toList());

            // 후기 가져오기 + (나중에)후기 이미지도 같이 가져오기 + 3개 정도만 가져오기
            List<ReviewDTO> reviews = reviewDAO.findReviewsByStoreId(dto.getId(), null);
//                    .stream()
//                    .map(reviewDTO -> {
//                        List<FileReviewDTO> reviewImages = reviewDAO.findImagesInReview(reviewDTO.getId());
//                        if(!reviewImages.isEmpty()) {
//                            reviewDTO.setReviewFiles(reviewImages);
//                        }
//                    });

            // 마지막 로그인 nn전
            String latestLogin = DateUtils.toRelativeTime(dto.getOwnerLatestLogin());
            dto.setOwnerLatestLogin(latestLogin);

            // 상품 중 10개 만 가져오기
            dto.setStoreItems(items.subList(0, Math.min(items.size(), 10)));

            // 상품 개수
            dto.setItemCount(items.size());

            // 후기 중 4개 정도만 가져오기
            dto.setStoreReviews(reviews.subList(0, Math.min(items.size(), 4)));

            // 후기 개수
            dto.setReviewCount(reviews.size());

            log.info("{}.......", dto);
        } else {
            log.info("상품 없음....");
        }
    }

    @Test
    public void testGetReviewsForDetail() {
        StoreReviewDTO storeReviewDTO = new StoreReviewDTO();
        int total = reviewDAO.getReviewCountByStoreId(129L);

        Criteria criteria = new Criteria(1, total);
        storeReviewDTO.setTotal(total);

        // 후기 조회 + (이미지도 가져와야 함)
        List<ReviewDTO> reviews = reviewDAO.findReviewsByStoreId(129L, criteria);

        criteria.setHasMore(reviews.size() > criteria.getRowCount());
        storeReviewDTO.setCriteria(criteria);

        if(criteria.isHasMore()) {
            reviews.remove(reviews.size() - 1);
        }

        reviews.forEach(review -> {
            review.setCreatedDatetime(DateUtils.toRelativeTime(review.getCreatedDatetime()));
        });
        storeReviewDTO.setStoreReviews(reviews);

        log.info("{}", storeReviewDTO);
    }
}
