package com.app.haetssal_jangteo.controller.user;

import com.app.haetssal_jangteo.dto.SellerDTO;
import com.app.haetssal_jangteo.dto.UserDTO;
//import com.app.haetssal_jangteo.service.mail.MailService;
import com.app.haetssal_jangteo.service.oauth.KaKaoService;
import com.app.haetssal_jangteo.service.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/user/**")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    public final UserService userService;
//    public final MailService mailService;
    private final HttpSession session;
    private final KaKaoService kaKaoService;

//    @GetMapping("check-code")
//    @ResponseBody
//    public boolean checkCode(String code) {
//
//    }

    @GetMapping("check-email")
    @ResponseBody
    public boolean checkEmail(String userEmail){
        return userService.checkEmail(userEmail);
    }

    @GetMapping("join")
    public String goToJoinForm(){
        return "join/join";
    }
    //    리다이렉트 안하면 새로고침할때마다 인서트되버림
    @PostMapping("join")
    public RedirectView join(UserDTO userDTO){
        userService.haetssalJoin(userDTO);
        return new RedirectView("/user/login");
    }

    @GetMapping("login")
    public String goToLoginForm(@CookieValue(name="remember", required = false) boolean remember,
                                @CookieValue(name="remember-user-email", required = false) String rememberUserEmail,
                                HttpServletRequest request,
                                Model model){
        model.addAttribute("remember", remember);
        model.addAttribute("rememberUserEmail", rememberUserEmail);
        return "login/login";
    }

    @PostMapping("login")
    public RedirectView login(UserDTO userDTO, Model model, HttpServletResponse response){
        session.setAttribute("user", userService.login(userDTO));

        Cookie rememberUserEmailCookie = new Cookie("remember-user-email", userDTO.getUserEmail());
        Cookie rememberCookie = new Cookie("remember", String.valueOf(userDTO.isRemember()));

        rememberUserEmailCookie.setPath("/");
        rememberCookie.setPath("/");

        if(userDTO.isRemember()){
            rememberUserEmailCookie.setMaxAge(60 * 60 * 24 * 30); // 30일 유지
            rememberCookie.setMaxAge(60 * 60 * 24 * 30); // 30일 유지

        }else{
            rememberUserEmailCookie.setMaxAge(0);
            rememberCookie.setMaxAge(0);
        }

        response.addCookie(rememberUserEmailCookie);
        response.addCookie(rememberCookie);

        return new RedirectView("/main/home");
    }

    @GetMapping("kakao/join")
    public String goToKakaoJoinForm(){
        return "join/social-join";
    }

//    리다이렉트 안하면 새로고침할때마다 인서트되버림
    @PostMapping("kakao/join")
    public RedirectView kakaoJoin(UserDTO userDTO){
        userService.kakaoJoin(userDTO);
        return new RedirectView("/user/login");
    }

    @GetMapping("seller-join")
    public String goToSellerJoinForm(){
        return "join/seller-join";
    }

    //    리다이렉트 안하면 새로고침할때마다 인서트되버림
    @PostMapping("seller-join")
    public RedirectView sellerJoin(UserDTO userDTO, SellerDTO sellerDTO){
        userService.haetssalSellerJoin(userDTO, sellerDTO);
        return new RedirectView("/user/login");
    }
}
