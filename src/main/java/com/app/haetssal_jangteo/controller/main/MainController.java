package com.app.haetssal_jangteo.controller.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    public RedirectView goToStoreList() {
        return new RedirectView("/store/list");
    }
}
