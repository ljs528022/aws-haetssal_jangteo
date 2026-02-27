package com.app.haetssal_jangteo.common.exception.handler;

import com.app.haetssal_jangteo.common.exception.KakaoLoginException;
import com.app.haetssal_jangteo.common.exception.LoginFailException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice(basePackages = {
        "com.app.haetssal_jangteo.controller.user",
        "com.app.haetssal_jangteo.controller.oauth"})
public class UserExceptionHandler {
    @ExceptionHandler(LoginFailException.class)
    protected RedirectView loginFail(LoginFailException loginFailException, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("login", "fail");
        return new RedirectView("/user/login");
    }

    @ExceptionHandler(KakaoLoginException.class)
    protected RedirectView KakaoFail(KakaoLoginException kakaoLoginException, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("alreadyExist", "error");
        return new RedirectView("/user/login");
    }
}
