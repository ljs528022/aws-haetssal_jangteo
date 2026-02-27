package com.app.haetssal_jangteo.mapper;


import com.app.haetssal_jangteo.common.enumeration.Provider;
import com.app.haetssal_jangteo.common.enumeration.User;
import com.app.haetssal_jangteo.domain.UserVO;
import com.app.haetssal_jangteo.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertTest() {
        UserDTO userDTO = new UserDTO();

        userDTO.setUserEmail("sokkomann@google.com");
        userDTO.setUserPassword("1234");
        userDTO.setUserPhone("01000000000");
        userDTO.setUserType(User.NORMAL);
        userDTO.setUserName("김민중");
//        userDTO.setUserIntro("안녕하세요.반갑");
        userDTO.setUserIntro("얘는 햇살.반갑");
        userDTO.setAuthProvider(Provider.HAETSSAL);

        userMapper.insert(userDTO);
        userMapper.insertOauth(userDTO.toOAuthVO());

    }

    @Test
    public void selectByEmailTest(){
        Optional<UserDTO> foundUser = userMapper.selectByEmail("test@naver.com");
        log.info("내가입력한 이메일 쓸수있니?{}", foundUser.isEmpty());

    }

    @Test
    public void selectUserForLoginTest(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmail("sokkomann@naver.com");
        userDTO.setUserPassword("1234");

        Optional<UserDTO> foundUser = userMapper.selectUserForLogin(userDTO);
        log.info("{}", foundUser.isPresent());
    }


}
