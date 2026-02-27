package com.app.haetssal_jangteo.controller.mypage;

import com.app.haetssal_jangteo.dto.PaymentDTO;
import com.app.haetssal_jangteo.dto.ReviewDTO;
import com.app.haetssal_jangteo.dto.UserDTO;
import com.app.haetssal_jangteo.service.profile.ProfileService;
import com.app.haetssal_jangteo.service.review.ReviewService;
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
    private final HttpSession session;

    @GetMapping("userpage")
    public String goToMypage(Model model){
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        Long userId = loggedinUser.getId();

        model.addAttribute("profileImage", profileService.getProfileImage(userId));
//        요건 옵셔널 -> 통으로 갖고와서 소개랑 이름이랑 방문수 등등...페이지 헤더부분
        model.addAttribute("userInfo", profileService.getUserProfile(userId).orElse(null));
        model.addAttribute("reviewCount", reviewService.getReivewCountByUserId(userId));
        model.addAttribute("reviewList", reviewService.getReviewListByUserId(userId));

        return "user/mypage";
    }

    //    5번탭으로 이동
    @GetMapping("complete-items")
    public List<PaymentDTO> completeItems() {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        Long userId = loggedinUser.getId();
        return reviewService.getCompletesListByUserId(userId);
    }

    //    리뷰 작성
    @PostMapping("review")
    @ResponseBody
    public String writeReview(ReviewDTO reviewDTO,
                              @RequestParam(defaultValue = "reviewImages", required = false) List<MultipartFile> files) {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        if (loggedinUser == null) {
            return "fail";
        }
        reviewDTO.setReviewUserId(loggedinUser.getId());
        reviewService.writeReview(reviewDTO, files);
        return "success";
    }
}
