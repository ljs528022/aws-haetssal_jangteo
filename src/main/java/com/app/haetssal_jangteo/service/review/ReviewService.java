package com.app.haetssal_jangteo.service.review;

import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.dto.*;
import com.app.haetssal_jangteo.repository.*;
import com.app.haetssal_jangteo.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //주입!
@Transactional(rollbackFor = Exception.class)
public class ReviewService {
    private final ReviewDAO reviewDAO;
    private final PaymentDAO paymentDAO;
    private final FileDAO fileDAO;
    private final UserDAO userDAO;
    private final ItemDAO itemDAO;

    //    유저 프로필 뿌리기
    public Optional<UserDTO> getUserProfile(Long id) {
        return userDAO.findUserById(id);
    }

//    5번탭에 거래완료된 애들 뿌리기 - 얘네 각각카드에 리뷰작성 버튼으로 리뷰쓰는거라 가져와야함
    public List<PaymentDTO> getCompletesListByUserId(Long userId) {
        return paymentDAO.findCompletesByUserId(userId);
    }

//    리뷰쓰기
    public void writeReview(ReviewDTO reviewDTO, List<MultipartFile> files) {
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        reviewDAO.saveReview(reviewDTO);

        FileDTO fileDTO = new FileDTO();
        FileReviewDTO fileReviewDTO = new FileReviewDTO();

        fileReviewDTO.setReviewId(reviewDTO.getId());

        files.forEach((eachFile) -> {
            if(eachFile.getOriginalFilename().isEmpty()) {
                return;
            }
            UUID uuid = UUID.randomUUID();
            fileDTO.setFilePath(todayPath);
            fileDTO.setFileSize(String.valueOf(eachFile.getSize()));
            fileDTO.setFileOriginName(eachFile.getOriginalFilename());
            fileDTO.setFileName(uuid.toString() + "_" + eachFile.getOriginalFilename());
            fileDTO.setFileType(eachFile.getContentType().contains("image") ? Filetype.IMAGE : Filetype.DOCUMENT);
            fileDAO.save(fileDTO);

            fileReviewDTO.setFileId(fileDTO.getId());
            fileReviewDTO.setReviewId(reviewDTO.getId());
            reviewDAO.saveImagesInReview(fileReviewDTO.toFileReviewVO());

            File directory = new File(rootPath + "/" + fileDTO.getFilePath());
            if(!directory.exists()){
                directory.mkdirs();
            }
            try {
                eachFile.transferTo(new File(path, fileDTO.getFileName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

//    2번탭에 작성한 후기 개수 카운트
    public int getReivewCountByUserId(Long userId) {
        return reviewDAO.findReviewCount(userId);
    }

//    2번탭에 리뷰목록 뿌리기
    public List<ReviewDTO> getReviewListByUserId(Long userId) {
        return reviewDAO.findReviewsByUserId(userId);
    }

//    가게 id로 해당 가게의 상품 후기 조회
    public StoreReviewDTO getReviewsByStoreId(Long storeId, int page) {
        StoreReviewDTO storeReviewDTO = new StoreReviewDTO();
        int total = reviewDAO.getReviewCountByStoreId(storeId);

        Criteria criteria = new Criteria(page, total);
        storeReviewDTO.setTotal(total);

        // 후기 조회 + (이미지도 가져와야 함)
        List<ReviewDTO> reviews = reviewDAO.findReviewsByStoreId(storeId, criteria).stream()
                .map(reviewDTO -> {
                    List<FileReviewDTO> reviewImages = reviewDAO.findImagesInReview(reviewDTO.getId());
                    if(!reviewImages.isEmpty()) {
                        reviewDTO.setReviewFiles(reviewImages);
                    }
                    return reviewDTO;
                }).collect(Collectors.toList());

        criteria.setHasMore(reviews.size() > criteria.getRowCount());
        storeReviewDTO.setCriteria(criteria);

        if(criteria.isHasMore()) {
            reviews.remove(reviews.size() - 1);
        }

        reviews.forEach(review -> {
            review.setCreatedDatetime(DateUtils.toRelativeTime(review.getCreatedDatetime()));
        });
        storeReviewDTO.setStoreReviews(reviews);

        return storeReviewDTO;
    }

//    상품 id로 해당 상품의 후기 조회
    public ItemReviewDTO getReviewsByItemId(Long itemId) {
        ItemReviewDTO itemReviewDTO = new ItemReviewDTO();
        int total = reviewDAO.getReviewCountByItemId(itemId);

        Criteria criteria = new Criteria(1, total);
        itemReviewDTO.setTotal(total);

        // 후기 조회 + (이미지도 가져와야 함)
        List<ReviewDTO> reviews = reviewDAO.findReviewsByItemId(itemId, criteria).stream()
                .map(reviewDTO -> {
                    List<FileReviewDTO> reviewImages = reviewDAO.findImagesInReview(reviewDTO.getId());
                    if(!reviewImages.isEmpty()) {
                        reviewDTO.setReviewFiles(reviewImages);
                    }
                    return reviewDTO;
                }).collect(Collectors.toList());

        criteria.setHasMore(reviews.size() > criteria.getRowCount());
        itemReviewDTO.setCriteria(criteria);

        if(criteria.isHasMore()) {
            reviews.remove(reviews.size() - 1);
        }

        reviews.forEach(review -> {
            review.setCreatedDatetime(DateUtils.toRelativeTime(review.getCreatedDatetime()));
        });
        itemReviewDTO.setItemReviews(reviews);

        return itemReviewDTO;
    }

//
    public String getTodayPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
