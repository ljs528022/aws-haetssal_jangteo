package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.CartItemVO;
import com.app.haetssal_jangteo.domain.CartVO;
import com.app.haetssal_jangteo.dto.CartDTO;
import com.app.haetssal_jangteo.dto.CartItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class CartMapperTests {

    @Autowired
    private CartMapper cartMapper;

    @Test
    public void testInsert() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserId(5L);
        cartMapper.insert(cartDTO.toVO());
    }

    @Test
    public void testSelectByUserId() {
        Optional<CartVO> foundCart = cartMapper.selectByUserId(2L);

        log.info("받아온 cart : {}", foundCart);
    }

    @Test
    public void testInsertCartItem() {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setCartId(4L);
        cartItemDTO.setUserId(5L);
        cartItemDTO.setItemId(2L);
        cartItemDTO.setItemName("명품 나주 배");
        cartItemDTO.setItemOption("명품 나주 배 10kg 한 박스");
        cartItemDTO.setItemPrice("40,000");

        cartMapper.insertCartItem(cartItemDTO.toCartItemVO());
    }

    @Test
    public void testSelectCartItems() {
        List<CartItemDTO> foundCartItems = cartMapper.selectCartItems(4L);

        log.info("받아온 장바구니 상품: {}", foundCartItems);
    }

    @Test
    public void testDeleteCartItem() {
        Long id = 1L;

        cartMapper.deleteCartItem(id);
    }

    @Test
    public void testDeleteAll() {
        Long cartId = 4L;
        cartMapper.deleteAll(cartId);
    }
}
