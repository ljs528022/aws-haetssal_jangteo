package com.app.haetssal_jangteo.service.cart;

import com.app.haetssal_jangteo.domain.CartVO;
import com.app.haetssal_jangteo.domain.ItemOptionVO;
import com.app.haetssal_jangteo.dto.CartDTO;
import com.app.haetssal_jangteo.dto.CartItemDTO;
import com.app.haetssal_jangteo.repository.CartDAO;
import com.app.haetssal_jangteo.repository.ItemDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CartService {
    private final CartDAO cartDAO;
    private final ItemDAO itemDAO;

    // 장바구니 id 조회
    public Long getCartIdByUserId(Long userId) {
        CartVO cart = getOrCreateCart(userId);
        return cart.getId();
    }

    // 장바구니에 담긴 상품들 조회
    public List<CartItemDTO> getCartItems(Long userId) {
        CartVO userCart = getOrCreateCart(userId);
        return cartDAO.findAllByCartId(userCart.getId());
    }

    // 장바구니에 상품 추가
    public void addCartItem(CartItemDTO cartItemDTO) {
        CartVO cart = getOrCreateCart(cartItemDTO.getUserId());
        cartItemDTO.setCartId(cart.getId());

        Arrays.stream(cartItemDTO.getOptionsToAdd()).forEach((optionId) -> {
            // optionId로 해당 옵션을 조회. 있으면 저장, 없으면 런타임 예외
            ItemOptionVO itemOptionVO = itemDAO.findOptionById(Long.valueOf(optionId))
                    .orElseThrow(() -> new RuntimeException("옵션을 찾을 수 없습니다: " + optionId));

            // 해당 옵션의 이름과 옵션이 같은 상품이 존재하는지 조회
            cartDAO.findSameItem(cart.getId(), itemOptionVO.getOptionName(), itemOptionVO.getOptionDetail())
                    .ifPresentOrElse(
                            // 있으면 해당 상품 수량 + 1
                            isPresent -> cartDAO.increaseCount(isPresent.getId()),
                            // 없으면 새로 추가
                            () -> {
                                cartItemDTO.setItemName(itemOptionVO.getOptionName());
                                cartItemDTO.setItemOption(itemOptionVO.getOptionDetail());
                                cartItemDTO.setItemPrice(itemOptionVO.getOptionPrice());
                                cartDAO.addCartItem(cartItemDTO.toCartItemVO());
                            }
                    );

        });
    }

    // 특정 상품 장바구니에서 제거
    public void removeCartItem(Long cartItemId) {
        cartDAO.removeCartItem(cartItemId);
    }

    // 장바구니 초기화
    public void removeAllCartItems(Long cartId) {
        cartDAO.removeAll(cartId);
    }


    // 장바구니가 있으면 가져오고, 없으면 새로 생성
    private CartVO getOrCreateCart(Long userId) {
        return cartDAO.findByUserId(userId).orElseGet(() -> {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setUserId(userId);
            cartDAO.createCart(cartDTO.toVO());
            return cartDAO.findByUserId(userId).orElseThrow();
        });
    }
}
