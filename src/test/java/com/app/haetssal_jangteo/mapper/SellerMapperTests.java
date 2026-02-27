package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.enumeration.Provider;
import com.app.haetssal_jangteo.common.enumeration.User;
import com.app.haetssal_jangteo.dto.SellerDTO;
import com.app.haetssal_jangteo.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class SellerMapperTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    SellerMapper sellerMapper;

    @Test
    public void insertTest() {
        UserDTO userDTO = new UserDTO();
        SellerDTO sellerDTO = new SellerDTO();

        userDTO.setUserEmail("aaaa@gmail.com");
        userDTO.setUserPassword("1234");
        userDTO.setUserPhone("01002226660");
        userDTO.setUserType(User.SELLER);
        userDTO.setUserName("김민중");
        userDTO.setAuthProvider(Provider.HAETSSAL);

        sellerDTO.setSellerBankName("국민은행");
        sellerDTO.setSellerDepositor("김민중");
        sellerDTO.setSellerAccountNumber("51354101114032");

        userMapper.insert(userDTO);
        userMapper.insertOauth(userDTO.toOAuthVO());
        sellerDTO.setId(userDTO.getId());
        sellerMapper.insert(sellerDTO);
    }
}
