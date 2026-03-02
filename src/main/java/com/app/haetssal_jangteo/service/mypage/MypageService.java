package com.app.haetssal_jangteo.service.mypage;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.PaymentSearch;
import com.app.haetssal_jangteo.dto.*;
import com.app.haetssal_jangteo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MypageService {
    private final ReviewDAO reviewDAO;
    private final PaymentDAO paymentDAO;
    private final FileItemDAO fileItemDAO;
    private final FileDAO fileDAO;
    private final UserDAO userDAO;

    //    유저 프로필 뿌리기
    public Optional<UserDTO> getUserProfile(Long id) {
        return userDAO.findUserById(id);
    }

    //    2번탭에 작성한 후기 개수 카운트
    public int getReivewCountByUserId(Long userId) {
        return reviewDAO.findReviewCount(userId);
    }

    //    2번탭에 후기목록 뿌리기
    public List<ReviewDTO> getReviewListByUserId(Long userId) {
        List<ReviewDTO> reviewList = reviewDAO.findReviewsByUserId(userId);
        reviewList.forEach(eachReview -> {
            eachReview.setReviewImages(reviewDAO.findImagesInReview(eachReview.getId()));
            List<FileItemDTO> itemFiles = fileItemDAO.findImagesById(eachReview.getReviewItemId());
            if (!itemFiles.isEmpty()) {
                eachReview.setItemFileName(itemFiles.get(0).getFileName());
                eachReview.setItemFileSavedPath(itemFiles.get(0).getFileSavedPath());
            }
        });
        return reviewList;
    }

    //    4번탭에 결제중인 상품 목록 뿌리기 (페이지네이션 + 정렬)
    public PaymentWithPagingDTO getPendingListByUserId(Long userId, int page, PaymentSearch search) {
        PaymentWithPagingDTO paging = new PaymentWithPagingDTO();

        int total = paymentDAO.findPendingCountByUserId(userId);

        Criteria criteria = new Criteria(page, total);
        paging.setTotal(total);

        List<PaymentDTO> items = paymentDAO.findPendingsByUserId(userId, criteria, search);
        criteria.setHasMore(items.size() > criteria.getRowCount());

        paging.setCriteria(criteria);
        if (criteria.isHasMore()) {
            items.remove(items.size() - 1);
        }

        items.forEach(eachCard -> {
            List<FileItemDTO> imageFiles = fileItemDAO.findImagesById(eachCard.getItemId());
            if (!imageFiles.isEmpty()) {
                eachCard.setFileName(imageFiles.get(0).getFileName());
                eachCard.setFileSavedPath(imageFiles.get(0).getFileSavedPath());
            }
        });

        paging.setPayments(items);
        return paging;
    }

    //    4번탭 결제중 상품 개수
    public int getPendingCountByUserId(Long userId) {
        return paymentDAO.findPendingCountByUserId(userId);
    }

    //    4번탭에서 결제 취소
    public void cancelPayment(Long id) {
        paymentDAO.cancelPayment(id);
    }

    //    5번탭에 거래완료된 애들 뿌리기 (페이지네이션 + 정렬)
    public PaymentWithPagingDTO getCompletesListByUserId(Long userId, int page, PaymentSearch search) {

        PaymentWithPagingDTO paging = new PaymentWithPagingDTO();
        int total = paymentDAO.findCompleteCountByUserId(userId);

        Criteria criteria = new Criteria(page, total);
        paging.setTotal(total);

        List<PaymentDTO> items = paymentDAO.findCompletesByUserId(userId, criteria, search);
        criteria.setHasMore(items.size() > criteria.getRowCount());

        paging.setCriteria(criteria);

        if (criteria.isHasMore()) {
            items.remove(items.size() - 1);
        }

        items.forEach(eachCard -> {
            List<FileItemDTO> imageFiles = fileItemDAO.findImagesById(eachCard.getItemId());

            if (!imageFiles.isEmpty()) {
                eachCard.setFileName(imageFiles.get(0).getFileName());
                eachCard.setFileSavedPath(imageFiles.get(0).getFileSavedPath());
            }

        });


        paging.setPayments(items);
        return paging;
    }

    //    5번탭 거래완료 상품 개수
    public int getCompleteCountByUserId(Long userId) {
        return paymentDAO.findCompleteCountByUserId(userId);
    }

    //    헤더 카운트
    public MypageCountDTO getCountsByUserId(Long userId) {
        MypageCountDTO counts = new MypageCountDTO();
        counts.setReviewCount(reviewDAO.findReviewCount(userId));
        counts.setPendingCount(paymentDAO.findPendingCountByUserId(userId));
        counts.setCompleteCount(paymentDAO.findCompleteCountByUserId(userId));
        return counts;
    }
}