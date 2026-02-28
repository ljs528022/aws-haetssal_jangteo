package com.app.haetssal_jangteo.controller.oauth;

import com.app.haetssal_jangteo.dto.UserDTO;
import com.app.haetssal_jangteo.service.oauth.KaKaoService;
import com.app.haetssal_jangteo.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequiredArgsConstructor
public class KaKaoController {
    private final KaKaoService kaKaoService;
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/kakao/login")
    public RedirectView kakaoLogin(String code, RedirectAttributes redirectAttributes){
        UserDTO userDTO = kaKaoService.kakaoLogin(code);
        String path = null;

        if(userDTO.getId() == null){
            redirectAttributes.addFlashAttribute("kakao", userDTO);
            path = "/user/kakao/join";
        }else {
            session.setAttribute("user", userDTO);
            path = "/main/home";
        }

        return new RedirectView(path);
    }
}
