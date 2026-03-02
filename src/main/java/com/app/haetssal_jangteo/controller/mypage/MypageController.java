package com.app.haetssal_jangteo.controller.mypage;

import com.app.haetssal_jangteo.common.search.PaymentSearch;
import com.app.haetssal_jangteo.dto.MypageCountDTO;
import com.app.haetssal_jangteo.dto.PaymentWithPagingDTO;
import com.app.haetssal_jangteo.dto.ReviewDTO;
import com.app.haetssal_jangteo.dto.UserDTO;
import com.app.haetssal_jangteo.service.mypage.MypageService;
import com.app.haetssal_jangteo.service.profile.ProfileService;
import com.app.haetssal_jangteo.service.review.ReviewService;
import com.app.haetssal_jangteo.service.store.StoreService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/mypage/**")
@RequiredArgsConstructor
@Slf4j
public class MypageController {
    private final ReviewService reviewService;
    private final ProfileService profileService;
    private final MypageService mypageService;
    private final HttpSession session;
    private final StoreService storeService;

    @GetMapping("userpage")
    public String goToMypage(Long id, Model model) {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        Long userId = loggedinUser.getId();

        model.addAttribute("profileImage", profileService.getProfileImage(userId));
        model.addAttribute("userInfo", profileService.getUserProfile(userId).orElse(null));
        model.addAttribute("reviewCount", mypageService.getReivewCountByUserId(userId));
        model.addAttribute("reviewList", mypageService.getReviewListByUserId(userId));
        model.addAttribute("storeId", storeService.findByUserId(id));

        return "user/mypage";
    }

    //    헤더 카운트
    @GetMapping("counts")
    @ResponseBody
    public MypageCountDTO getCounts() {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        Long userId = loggedinUser.getId();
        return mypageService.getCountsByUserId(userId);
    }

    //    2번탭으로 이동
    @GetMapping("reviewed-items")
    @ResponseBody
    public List<ReviewDTO> reviewedItems() {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        Long userId = loggedinUser.getId();
        return mypageService.getReviewListByUserId(userId);
    }

    //    4번탭으로 이동
    @GetMapping("pending-items/{page}")
    @ResponseBody
    public PaymentWithPagingDTO pendingItems(@PathVariable int page,
                                             PaymentSearch search) {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        Long userId = loggedinUser.getId();
        return mypageService.getPendingListByUserId(userId, page, search);
    }

    //    5번탭으로 이동
    @GetMapping("complete-items/{page}")
    @ResponseBody
    public PaymentWithPagingDTO completeItems(@PathVariable int page,
                                              PaymentSearch search) {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        Long userId = loggedinUser.getId();
        return mypageService.getCompletesListByUserId(userId, page, search);
    }

    //    결제 취소
    @PostMapping("payment/{id}/cancel")
    @ResponseBody
    public String cancelPayment(@PathVariable Long id) {
        mypageService.cancelPayment(id);
        return "success";
    }

    //    리뷰 작성
    @PostMapping("review")
    @ResponseBody
    public String writeReview(ReviewDTO reviewDTO,
                              @RequestParam(value = "reviewImageFiles", required = false) List<MultipartFile> files) {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        if (loggedinUser == null) {
            return "fail";
        }
        reviewDTO.setReviewUserId(loggedinUser.getId());
        reviewService.writeReview(reviewDTO, files);
        return "success";
    }

    //    리뷰 수정
    @PostMapping("review/update")
    @ResponseBody
    public String updateReview(ReviewDTO reviewDTO) {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        if (loggedinUser == null) {
            return "fail";
        }
        reviewService.updateReview(reviewDTO);
        return "success";
    }

    //    리뷰 삭제
    @PostMapping("review/{id}/delete")
    @ResponseBody
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "success";
    }

}