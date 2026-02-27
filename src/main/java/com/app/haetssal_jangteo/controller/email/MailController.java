//package com.app.haetssal_jangteo.controller.email;
//
//
//import com.app.haetssal_jangteo.service.mail.MailService;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.view.RedirectView;
//
//// 비동기라면(우리 햇살장터는 비동기) 레스트컨트롤러
////@RestController
//@Controller
//@RequiredArgsConstructor
//@Slf4j
//public class MailController {
//    private final MailService mailService;
//    private final HttpServletResponse response;
//
////    @GetMapping("check-code")
////    @ResponseBody
//
//    @PostMapping
//
//    @GetMapping("confirm")
////    @CookieValue(value = "code", required = false) 해줘야 널이됨
//    public RedirectView confirm(@CookieValue(value = "code", required = false) String cookieCode, String code)
//}
