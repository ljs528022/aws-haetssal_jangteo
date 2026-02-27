package com.app.haetssal_jangteo.service.user;

import com.app.haetssal_jangteo.common.enumeration.Provider;
import com.app.haetssal_jangteo.common.enumeration.User;
import com.app.haetssal_jangteo.common.exception.LoginFailException;
import com.app.haetssal_jangteo.domain.UserVO;
import com.app.haetssal_jangteo.dto.*;
import com.app.haetssal_jangteo.repository.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor //주입!
@Transactional(rollbackFor = Exception.class)
public class UserService {
//    주입!
    private final UserDAO userDAO;

//==========================회원가입, 로그인============================

//    이메일검사. 쓸숭있나요? => true
    public boolean checkEmail(String memberEmail) {
        return userDAO.findByUserEmail(memberEmail).isEmpty();
    }

//    햇살로 일반회원 회원가입
    public void haetssalJoin(UserDTO userDTO) {
        userDTO.setUserType(User.NORMAL);
        userDTO.setAuthProvider(Provider.HAETSSAL);
        userDAO.save(userDTO);
        userDAO.saveOAuth(userDTO.toOAuthVO());
    }

//    햇살로 판매자 회원가입
    public void haetssalSellerJoin(UserDTO userDTO, SellerDTO sellerDTO) {
        userDTO.setUserType(User.SELLER);
        userDTO.setAuthProvider(Provider.HAETSSAL);
        userDAO.save(userDTO);
        userDAO.saveOAuth(userDTO.toOAuthVO());
        sellerDTO.setId(userDTO.getId());
        userDAO.saveSeller(sellerDTO);
    }

//    카카오로 회원가입.(로그인 화면에서 kakao버튼을 눌러서 진행)
    public void kakaoJoin(UserDTO userDTO) {
        userDTO.setUserType(User.NORMAL);
        userDTO.setAuthProvider(Provider.SOCIAL);
        userDAO.save(userDTO);
        userDAO.saveOAuth(userDTO.toOAuthVO());
    }

    // 로그인
    public UserDTO login(UserDTO userDTO) {
        Optional<UserDTO> foundUser = userDAO.findForLogin(userDTO);
        UserDTO loginedUser = foundUser.orElseThrow(LoginFailException::new);
        userDAO.setUserVisit(loginedUser.getId());

        return loginedUser;
    }

    public UserDTO toDTO(UserVO userVO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userVO.getId());
        userDTO.setUserEmail(userVO.getUserEmail());
        userDTO.setUserName(userVO.getUserName());
        userDTO.setUserType(userVO.getUserType());
        userDTO.setUserState(userVO.getUserState());
        userDTO.setCreatedDatetime(userVO.getCreatedDatetime());
        userDTO.setUpdatedDatetime(userVO.getUpdatedDatetime());
        return userDTO;
    }
}
