package com.app.haetssal_jangteo.controller.cart;

import com.app.haetssal_jangteo.mapper.CartMapper;
import com.app.haetssal_jangteo.repository.CartDAO;
import com.app.haetssal_jangteo.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart/**")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/list")
    public String goToCart(Long userId, Model model) {
        model.addAttribute("cartId", cartService.getCartIdByUserId(userId));
        model.addAttribute("cartItems", cartService.getCartItems(userId));
        return "payment/itemCart";
    }

}
