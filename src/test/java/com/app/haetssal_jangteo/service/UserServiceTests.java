package com.app.haetssal_jangteo.service;

import com.app.haetssal_jangteo.common.enumeration.User;
import com.app.haetssal_jangteo.dto.SellerDTO;
import com.app.haetssal_jangteo.dto.UserDTO;
import com.app.haetssal_jangteo.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    public void haetssalJoinTest() {
        UserDTO userDTO = new UserDTO();

        userDTO.setUserEmail("mango@naver.com");
        userDTO.setUserPassword("1234");
        userDTO.setUserPhone("01032323232");
        userDTO.setUserName("김민중인데");

        userService.haetssalJoin(userDTO);
    }

//    kakaoJoin, 이름은 join인데 login화면에서 카카오버튼을 눌러서 가입되는것.
    @Test
    public void kakaoJoinTest() {
        UserDTO userDTO = new UserDTO();

        userDTO.setUserEmail("asdf@naver.com");
        userDTO.setUserPassword("1234");
        userDTO.setUserPhone("01033333333");
        userDTO.setUserName("김민중인데");
        userDTO.setUserIntro("카카오조인테스트요");

        userService.kakaoJoin(userDTO);
    }

    @Test
    public void haetssalSellerJoinTest() {
        UserDTO userDTO = new UserDTO();
        SellerDTO sellerDTO = new SellerDTO();

        userDTO.setUserEmail("sokkomann@naver.com");
        userDTO.setUserPassword("1234");
        userDTO.setUserPhone("0105555");
        userDTO.setUserName("테스트");
        userDTO.setUserType(User.SELLER);
        sellerDTO.setSellerBankName("테스트은행");
        sellerDTO.setSellerDepositor("테스트");
        sellerDTO.setSellerAccountNumber("0987654321");

        userService.haetssalSellerJoin(userDTO, sellerDTO);
    }
}
