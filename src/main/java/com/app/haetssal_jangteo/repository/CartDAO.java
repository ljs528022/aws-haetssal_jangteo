package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.domain.CartItemVO;
import com.app.haetssal_jangteo.domain.CartVO;
import com.app.haetssal_jangteo.dto.CartItemDTO;
import com.app.haetssal_jangteo.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartDAO {
    private final CartMapper cartMapper;

    // 장바구니 생성 (유저 당 딱 한번)
    public void createCart(CartVO cartVO) {
        cartMapper.insert(cartVO);
    }

    // 유저 id로 장바구니 조회
    public Optional<CartVO> findByUserId(Long userId) {
        return cartMapper.selectByUserId(userId);
    }

    // 장바구니의 상품 전체 조회
    public List<CartItemDTO> findAllByCartId(Long cartId) {
        return cartMapper.selectCartItems(cartId);
    }

    // 장바구니에 담긴 '같은' 상품 조회
    public Optional<CartItemVO> findSameItem(Long cartId, String itemName, String itemOption) {
        return cartMapper.selectSameItem(cartId, itemName, itemOption);
    }

    // 장바구니에 상품 담기
    public void addCartItem(CartItemVO cartItemVO) {
        cartMapper.insertCartItem(cartItemVO);
    }

    // 상품 개수 증가
    public void increaseCount(Long id) {
        cartMapper.updateCartItemCount(id);
    }

    // 장바구니에서 특정 상품 제거
    public void removeCartItem(Long id) {
        cartMapper.deleteCartItem(id);
    }

    // 장바구니 초기화
    public void removeAll(Long cartId) {
        cartMapper.deleteAll(cartId);
    }

}
