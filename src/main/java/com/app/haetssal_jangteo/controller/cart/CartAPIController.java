package com.app.haetssal_jangteo.controller.cart;

import com.app.haetssal_jangteo.dto.CartItemDTO;
import com.app.haetssal_jangteo.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/**")
@RequiredArgsConstructor
@Slf4j
public class CartAPIController {
    private final CartService cartService;

    @PostMapping("/item")
    public void addCartItem(@RequestBody CartItemDTO cartItemDTO) {
        log.info("받아온 cartItemDTO : {}", cartItemDTO);
        cartService.addCartItem(cartItemDTO);
    }

    // 장바구니에서 해당 상품 제거
    @DeleteMapping("/delete/{cartItemId}")
    public void deleteCartItem(@PathVariable Long cartItemId) {
        log.info("제거할 장바구니 상품 id : {}", cartItemId);
        cartService.removeCartItem(cartItemId);
    }

    // 장바구니에 모든 상품 제거
    @DeleteMapping("/deleteAll/{cartId}")
    public void deleteAll(@PathVariable Long cartId) {
        log.info("받아온 장바구니 id : {}", cartId);
        cartService.removeAllCartItems(cartId);
    }
}
