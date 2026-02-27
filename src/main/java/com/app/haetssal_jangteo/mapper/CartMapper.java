package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.CartItemVO;
import com.app.haetssal_jangteo.domain.CartVO;
import com.app.haetssal_jangteo.dto.CartDTO;
import com.app.haetssal_jangteo.dto.CartItemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CartMapper {

    // 장바구니 생성 (유저당 딱 한번)
    public void insert(CartVO cartVO);

    // 유저 id로 장바구니 조회
    public Optional<CartVO> selectByUserId(Long userId);

    // 장바구니 아이템 목록 조회
    public List<CartItemDTO> selectCartItems(Long cartId);

    // 장바구니에 담긴 '같은' 상품 조회
    public Optional<CartItemVO> selectSameItem(@Param("cartId")Long cartId, @Param("itemName")String itemName, @Param("itemOption")String itemOption);

    // 장바구니에 상품 담기
    public void insertCartItem(CartItemVO cartItemVO);

    // 상품 수량 증가
    public void updateCartItemCount(Long id);

    // 장바구니에서 상품 빼기
    public void deleteCartItem(Long id);

    // 장바구니 초기화
    public void deleteAll(Long cartId);
}
