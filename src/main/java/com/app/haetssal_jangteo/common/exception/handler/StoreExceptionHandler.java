package com.app.haetssal_jangteo.common.exception.handler;

import com.app.haetssal_jangteo.common.exception.StoreNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice(basePackages = "com.app.haetssal_jangteo.controller.store")
public class StoreExceptionHandler {
    @ExceptionHandler(StoreNotFoundException.class)
    protected RedirectView foundFail(StoreNotFoundException foundException, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("found", "fail");
        return new RedirectView("/store/list");
    }
}
