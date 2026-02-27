package com.app.haetssal_jangteo.common.exception.handler;

import com.app.haetssal_jangteo.common.exception.ItemNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice(basePackages = "com.app.haetssal_jangteo.controller.item")
public class ItemExceptionHandler {
    @ExceptionHandler(ItemNotFoundException.class)
    protected RedirectView foundFail(ItemNotFoundException writeFailException, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("found", "fail");
        return new RedirectView("/item/list");
    }
}
