package com.app.haetssal_jangteo.controller.profile;

import com.app.haetssal_jangteo.dto.DeliveryDTO;
import com.app.haetssal_jangteo.dto.FileUserDTO;
import com.app.haetssal_jangteo.dto.UserDTO;
import com.app.haetssal_jangteo.dto.UserNameDTO;
import com.app.haetssal_jangteo.service.profile.ProfileService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/profile/**")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {
    private final ProfileService profileService;
    private final HttpSession session;

    @GetMapping("modify")
    public String goToModifyForm(Model model) {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        Long userId = loggedinUser.getId();
//        이미지만
        model.addAttribute("profileImage", profileService.getProfileImage(userId));
//        옵셔널로 이름이랑 프사랑 등등
        model.addAttribute("userInfo", profileService.getUserProfile(userId).orElse(null));

        return "user/modify";
    }

//    이미지 변경
    @PostMapping("image")
    @ResponseBody
    public String changeProfileImage(FileUserDTO fileUserDTO,
                                     @RequestParam("file") MultipartFile file) {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        if (loggedinUser == null) {
            return "fail";
        }
        fileUserDTO.setUserId(loggedinUser.getId());
        profileService.saveProfileImage(fileUserDTO, file);
        return "success";
    }

//    이름?닉? 변경
    @PostMapping("name")
    @ResponseBody
    public String changeName(@RequestBody UserDTO userDTO) {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        if (loggedinUser == null) {
            return "fail";
        }
        userDTO.setId(loggedinUser.getId());
        profileService.setUserName(userDTO);
        return "success";
    }

//    소개 변경
    @PostMapping("intro")
    @ResponseBody
    public String changeIntro(@RequestBody UserDTO userDTO) {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        if (loggedinUser == null) {
            return "fail";
        }
        userDTO.setId(loggedinUser.getId());
        profileService.setUserInfo(userDTO);
        return "success";
    }

//    비번 쳌
    @PostMapping("check-password")
    @ResponseBody
    public boolean checkPassword(@RequestBody UserDTO userDTO) {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
//        return profileService.checkPassword(loggedinUser.getId(), userDTO.getUserPassword());
        Long userId = loggedinUser.getId();
        String inputPw = userDTO.getUserPassword();
        String dbPw = profileService.getUserProfile(userId).get().getUserPassword();
        System.out.println("입력값: " + inputPw);
        System.out.println("디비비번: " + dbPw);
        System.out.println("같냐: " + dbPw.equals(inputPw));
        return profileService.checkPassword(userId, inputPw);
    }

//    비번변경하기
    @PostMapping("password")
    @ResponseBody
    public String changePassword(@RequestBody UserDTO userDTO) {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        if (loggedinUser == null) {
            return "fail";
        }
        userDTO.setId(loggedinUser.getId());
        profileService.changePassword(userDTO);
        return "success";
    }

//    배송지 목록 뿌리기
    @GetMapping("delivery-list")
    @ResponseBody
    public List<DeliveryDTO> getDeliveryList() {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        return profileService.getDeliveryList(loggedinUser.getId());
    }

//    배송지 추가
    @PostMapping("delivery")
    @ResponseBody
    public String addDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        if (loggedinUser == null) {
            return "fail";
        }
        deliveryDTO.setUserId(loggedinUser.getId());
        profileService.addDelivery(deliveryDTO);
        return "success";
    }

//    배송지 삭제
    @DeleteMapping("delivery")
    @ResponseBody
    public String removeDelivery(@RequestParam Long id) {
        UserDTO loggedinUser = (UserDTO) session.getAttribute("user");
        if (loggedinUser == null) {
            return "fail";
        }
        profileService.removeDelivery(id);
        return "success";
    }
}
